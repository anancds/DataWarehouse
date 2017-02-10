/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/10 15:04
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.httpclient.common;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;

/**
 * <p></p>
 * author chendongsheng5 2017/2/10 15:04
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/10 15:04
 * modify by reason:{方法名}:{原因}
 */
public class HttpCookies {
  /**
   * 使用httpcontext，用于设置和携带Cookie
   */
  private HttpClientContext context ;

  /**
   * 储存Cookie
   */
  private CookieStore cookieStore;

  public static HttpCookies custom(){
    return new HttpCookies();
  }

  private HttpCookies(){
    this.context = new HttpClientContext();
    this.cookieStore = new BasicCookieStore();
    this.context.setCookieStore(cookieStore);
  }

  public HttpClientContext getContext() {
    return context;
  }

  public HttpCookies setContext(HttpClientContext context) {
    this.context = context;
    return this;
  }

  public CookieStore getCookieStore() {
    return cookieStore;
  }

  public HttpCookies setCookieStore(CookieStore cookieStore) {
    this.cookieStore = cookieStore;
    return this;
  }
}
