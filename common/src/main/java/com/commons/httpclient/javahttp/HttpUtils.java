/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/9 15:55
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.httpclient.javahttp;

import com.hikvision.mdp.commons.exception.HttpProcessException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/**
 * <p>Http协议工具类</p>
 * author chendongsheng5 2017/2/9 15:55
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/9 15:55
 * modify by reason:{方法名}:{原因}
 */
public class HttpUtils extends CommonHttpUtils {

  @Override
  protected URLConnection createRequest(String strUrl, String strMethod)
      throws HttpProcessException {
    // 根据URL取得一个连接
    URLConnection conn = getRequestByUrl(strUrl);
    // 判断该连接是否 HTTP 连接
    if (conn instanceof HttpsURLConnection || !(conn instanceof HttpURLConnection)) {
      throw new HttpProcessException("This URL: " + strUrl + " can not create HTTP connection.");
    }
    // 设置HTTP相关配置：提交方式
    try {
      HttpURLConnection httpConn = (HttpURLConnection) conn;
      httpConn.setRequestMethod(strMethod);
    } catch (IOException e) {
      throw new HttpProcessException(e.getMessage(), e);
    }

    return conn;
  }
}
