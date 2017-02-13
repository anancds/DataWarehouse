/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/9 15:36
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.httpclient.javahttp;


import com.hikvision.mdp.commons.exception.HttpProcessException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>java 自带的url的工具类</p>
 * author chendongsheng5 2017/2/9 15:36
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/9 15:36
 * modify by reason:{方法名}:{原因}
 */
public abstract class CommonHttpUtils {

  private static final Logger LOG = LogManager.getLogger(CommonHttpUtils.class);

  public static void close(InputStream c) {
    try {
      if (c != null) {
        c.close();
      }
    } catch (Exception ex) {
    }
  }

  public static void close(OutputStream c) {
    try {
      if (c != null) {
        c.close();
      }
    } catch (Exception ex) {
    }
  }

  /**
   * 根据URL取得一个连接
   *
   * @param urlStr 输入的url
   * @return url连接
   * @throws HttpProcessException 自定义的exception
   */
  protected URLConnection getRequestByUrl(String urlStr) throws HttpProcessException {
    URL url = null;
    URLConnection conn = null;
    try {
      Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8087));
      url = new URL(urlStr);
      conn = url.openConnection(proxy);
      conn.setDoInput(true);
      conn.setDoOutput(true);
    } catch (IOException e) {
      throw new HttpProcessException(e);
    }

    return conn;
  }

  /**
   * 与服务器建立连接（HTTP、或HTTPS）
   */
  protected abstract URLConnection createRequest(String strUrl, String strMethod)
      throws HttpProcessException;

  /**
   * 发送请求
   *
   * @param contentType 报文头
   * @throws HttpProcessException 自定义exception
   */
  public String send(String date, String url, String contentType) throws HttpProcessException {
    return send(date, url, contentType, Charset.defaultCharset().name());
  }

  /**
   * @param contentType 报文头
   * @throws HttpProcessException 自定义exception
   */
  public String send(String date, String url, String contentType, String encoding)
      throws HttpProcessException {

    // 1、重新对请求报文进行 GBK 编码
    byte[] postData;
    try {
      postData = date.getBytes(encoding);
    } catch (UnsupportedEncodingException e) {
      throw new HttpProcessException(e);
    }

    // 2、发送 HTTPS 请求
    OutputStream reqStream = null;
    InputStream resStream = null;
    URLConnection request = null;
    String respText = null;
    try {
      //A、与服务器建立 HTTPS 连接
      request = createRequest(url, "POST");

      //B、指定报文头【Content-type】
      if (contentType == null || contentType.length() == 0) {
        request.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
      } else {
        request.setRequestProperty("Content-type", contentType);
      }
//            request.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
      //   指定报文头【Content-length】 与 【Keep-alive】
      request.setRequestProperty("Content-length", String.valueOf(postData.length));
      request.setRequestProperty("Keep-alive", "false");

      //C、发送报文至服务器
      reqStream = request.getOutputStream();
      reqStream.write(postData);
      reqStream.close();

      //D、接收服务器返回结果
      ByteArrayOutputStream ms = null;
      resStream = request.getInputStream();
      ms = new ByteArrayOutputStream();
      byte[] buf = new byte[4096];
      int count;
      while ((count = resStream.read(buf, 0, buf.length)) > 0) {
        ms.write(buf, 0, count);
      }
      resStream.close();
      respText = new String(ms.toByteArray(), encoding);
      LOG.info("The response result is：");
      LOG.info(respText);
    } catch (UnknownHostException e) {
      throw new HttpProcessException("Can not reach the server 【" + e.getMessage() + "】", e);
    } catch (IOException e) {
      throw new HttpProcessException(e.getMessage(), e);
    } finally {
      CommonHttpUtils.close(reqStream);
      CommonHttpUtils.close(resStream);
    }

    return respText;
  }
}
