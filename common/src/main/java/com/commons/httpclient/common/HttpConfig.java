/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/10 10:31
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.httpclient.common;

import com.hikvision.mdp.commons.exception.HttpProcessException;
import com.hikvision.mdp.commons.httpclient.MdpHttpAsyncClient.IHandler;
import com.hikvision.mdp.commons.httpclient.builder.HttpAsyncClientBuilderV2;
import com.hikvision.mdp.commons.httpclient.builder.HttpClientBuilderV2;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.protocol.HttpContext;


/**
 * <p>Http请求配置类</p>
 * author chendongsheng5 2017/2/10 10:31
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/10 10:31
 * modify by reason:{方法名}:{原因}
 */
public class HttpConfig {
  private HttpConfig(){};

  /**
   * 获取实例
   * @return
   */
  public static HttpConfig custom(){
    return new HttpConfig();
  }

  /**
   * HttpClient对象
   */
  private CloseableHttpClient client;

  /**
   * CloseableHttpAsyncClient对象
   */
  private CloseableHttpAsyncClient asynclient;

  /**
   * HttpClientBuilderV2对象，用于创建HttpClient对象
   */
  private HttpClientBuilderV2 hcb;

  /**
   * HttpAsyncClientBuilderV2对象，用于创建CloseableHttpAsyncClient对象
   */
  private HttpAsyncClientBuilderV2 hacb;

  /**
   * 资源url
   */
  private String url;

  /**
   * Header头信息
   */
  private Header[] headers;

  /**
   * 是否返回response的headers
   */
  private boolean isReturnRespHeaders;

  /**
   * 请求方法
   */
  private HttpMethods method=HttpMethods.GET;

  /**
   * 请求方法名称
   */
  private String methodName;

  /**
   * 用于cookie操作
   */
  private HttpContext context;

  /**
   * 传递参数
   */
  private Map<String, Object> map;

  /**
   * 以json格式作为输入参数
   */
  private String json;

  /**
   * 输入输出编码
   */
  private String encoding=Charset.defaultCharset().displayName();

  /**
   * 输入编码
   */
  private String inenc;

  /**
   * 输出编码
   */
  private String outenc;

  /**
   * 解决多线程下载时，strean被close的问题
   */
  private static final ThreadLocal<OutputStream> outs = new ThreadLocal<OutputStream>();
//	/**
//	 * 输出流对象
//	 */
//	private OutputStream out;

  /**
   * 异步操作回调执行器
   */
  private IHandler handler;

  /**
   * HttpClient对象
   */
  public HttpConfig client(CloseableHttpClient client) {
    this.client = client;
    return this;
  }

  /**
   * CloseableHttpAsyncClient对象
   */
  public HttpConfig asynclient(CloseableHttpAsyncClient asynclient) {
    this.asynclient = asynclient;
    return this;
  }

  /**
   * HttpClientBuilderV2对象，用于自动从连接池中获得HttpClient对象<br>
   * <font color="red"><b>请调用hcb.pool方法设置连接池</b></font>
   * @throws HttpProcessException
   */
  public HttpConfig hcb(HttpClientBuilderV2 hcb) throws HttpProcessException {
    this.hcb = hcb;
    return this;
  }

  /**
   * HttpAsyncClientBuilderV2对象，用于自动从连接池中获得CloseableHttpAsyncClient对象<br>
   * <font color="red"><b>请调用hacb.pool方法设置连接池</b></font>
   * @throws HttpProcessException
   */
  public HttpConfig asynclient(HttpAsyncClientBuilderV2 hacb) throws HttpProcessException {
    this.hacb = hacb;
    return this;
  }

  /**
   * 资源url
   */
  public HttpConfig url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Header头信息
   */
  public HttpConfig headers(Header[] headers) {
    this.headers = headers;
    return this;
  }

  /**
   * Header头信息(是否返回response中的headers)
   */
  public HttpConfig headers(Header[] headers, boolean isReturnRespHeaders) {
    this.headers = headers;
    this.isReturnRespHeaders=isReturnRespHeaders;
    return this;
  }

  /**
   * 请求方法
   */
  public HttpConfig method(HttpMethods method) {
    this.method = method;
    return this;
  }

  /**
   * 请求方法
   */
  public HttpConfig methodName(String methodName) {
    this.methodName = methodName;
    return this;
  }

  /**
   * cookie操作相关
   */
  public HttpConfig context(HttpContext context) {
    this.context = context;
    return this;
  }

  /**
   * 传递参数
   */
  public HttpConfig map(Map<String, Object> map) {
    synchronized (getClass()) {
      if(this.map==null || map==null){
        this.map = map;
      }else {
        this.map.putAll(map);;
      }
    }
    return this;
  }

  /**
   * 以json格式字符串作为参数
   */
  public HttpConfig json(String json) {
    this.json = json;
    map = new HashMap<String, Object>();
    map.put(CommonUtils.ENTITY_STRING, json);
    return this;
  }

  /**
   * 上传文件时用到
   */
  public HttpConfig files(String[] filePaths) {
    return files(filePaths, "file");
  }
  /**
   * 上传文件时用到
   * @param filePaths		待上传文件所在路径
   */
  public HttpConfig files(String[] filePaths, String inputName) {
    return files(filePaths, inputName, false);
  }
  /**
   * 上传文件时用到
   * @param filePaths			待上传文件所在路径
   * @param inputName		即file input 标签的name值，默认为file
   * @param forceRemoveContentTypeChraset
   * @return
   */
  public HttpConfig files(String[] filePaths, String inputName, boolean forceRemoveContentTypeChraset) {
    synchronized (getClass()) {
      if(this.map==null){
        this.map= new HashMap<String, Object>();
      }
    }
    map.put(CommonUtils.ENTITY_MULTIPART, filePaths);
    map.put(CommonUtils.ENTITY_MULTIPART+".name", inputName);
    map.put(CommonUtils.ENTITY_MULTIPART+".rmCharset", forceRemoveContentTypeChraset);
    return this;
  }

  /**
   * 输入输出编码
   */
  public HttpConfig encoding(String encoding) {
    //设置输入输出
    inenc(encoding);
    outenc(encoding);
    this.encoding = encoding;
    return this;
  }

  /**
   * 输入编码
   */
  public HttpConfig inenc(String inenc) {
    this.inenc = inenc;
    return this;
  }

  /**
   * 输出编码
   */
  public HttpConfig outenc(String outenc) {
    this.outenc = outenc;
    return this;
  }

  /**
   * 输出流对象
   */
  public HttpConfig out(OutputStream out) {
    outs.set(out);
    return this;
  }

  /**
   * 异步操作回调执行器
   */
  public HttpConfig handler(IHandler handler) {
    this.handler = handler;
    return this;
  }

  public CloseableHttpClient client() {
    return client;
  }

  public CloseableHttpAsyncClient asynclient() {
    return asynclient;
  }

  public HttpClientBuilderV2 hcb() {
    return hcb;
  }

  public HttpAsyncClientBuilderV2 hacb() {
    return hacb;
  }

  public Header[] headers() {
    return headers;
  }
  public boolean isReturnRespHeaders() {
    return isReturnRespHeaders;
  }

  public String url() {
    return url;
  }

  public HttpMethods method() {
    return method;
  }

  public String methodName() {
    return methodName;
  }

  public HttpContext context() {
    return context;
  }

  public Map<String, Object> map() {
    return map;
  }

  public String json() {
    return json;
  }

  public String encoding() {
    return encoding;
  }

  public String inenc() {
    return inenc == null ? encoding : inenc;
  }

  public String outenc() {
    return outenc == null ? encoding : outenc;
  }

  public OutputStream out() {
    return outs.get();
  }

  public boolean flushAndClose() throws IOException {
    if(out()!=null){
      try {
        out().flush();
        out().close();
      } catch (IOException e) {
        throw e;
      }finally{
        outs.remove(); //防止内存泄漏
      }
      return true;
    }
    return false;
  }

  public IHandler handler() {
    return handler;
  }
}
