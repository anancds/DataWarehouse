/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/10 10:25
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.httpclient.builder;

import com.hikvision.mdp.commons.exception.HttpProcessException;
import com.hikvision.mdp.commons.httpclient.common.SSLHelper;
import com.hikvision.mdp.commons.httpclient.common.SSLHelper.SSLProtocolVersion;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NoopIOSessionStrategy;
import org.apache.http.nio.conn.SchemeIOSessionStrategy;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

/**
 * <p>httpAsyncClient创建</p>
 * author chendongsheng5 2017/2/10 10:25
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/10 10:25
 * modify by reason:{方法名}:{原因}
 */
public class HttpAsyncClientBuilderV2 extends HttpAsyncClientBuilder {

  public boolean isSetPool = false;//记录是否设置了连接池
  private boolean isNewSSL = false;//记录是否设置了更新了ssl
  private SSLProtocolVersion sslpv = SSLProtocolVersion.SSLv3;//ssl 协议版本

  //用于配置ssl
  private SSLHelper ssls = SSLHelper.getInstance();

  private HttpAsyncClientBuilderV2() {
  }

  public static HttpAsyncClientBuilderV2 custom() {
    return new HttpAsyncClientBuilderV2();
  }

  /**
   * 设置超时时间
   *
   * @param timeout 超市时间，单位-毫秒
   */
  public HttpAsyncClientBuilderV2 timeout(int timeout) {
    // 配置请求的超时设置
    RequestConfig config = RequestConfig.custom()
        .setConnectionRequestTimeout(timeout)
        .setConnectTimeout(timeout)
        .setSocketTimeout(timeout)
        .build();
    return (HttpAsyncClientBuilderV2) this.setDefaultRequestConfig(config);
  }

  /**
   * 设置ssl安全链接
   */
  public HttpAsyncClientBuilderV2 ssl() throws HttpProcessException {
    if (isSetPool) {//如果已经设置过线程池，那肯定也就是https链接了
      if (isNewSSL) {
        throw new HttpProcessException("请先设置ssl，后设置pool");
      }
      return this;
    }
    // Create a registry of custom connection session strategies for supported
    // protocol schemes.
    Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
        .register("http", NoopIOSessionStrategy.INSTANCE)
        .register("https", ssls.getSSLIOSS(sslpv))
        .build();
    //配置io线程
    IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
        .setIoThreadCount(Runtime.getRuntime().availableProcessors()).build();
    //设置连接池大小
    ConnectingIOReactor ioReactor;
    try {
      ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
    } catch (IOReactorException e) {
      throw new HttpProcessException(e);
    }
    PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(
        ioReactor, null, sessionStrategyRegistry, null);
    return (HttpAsyncClientBuilderV2) this.setConnectionManager(connManager);
  }


  /**
   * 设置自定义sslcontext
   *
   * @param keyStorePath 密钥库路径
   */
  public HttpAsyncClientBuilderV2 ssl(String keyStorePath) throws HttpProcessException {
    return ssl(keyStorePath, "nopassword");
  }

  /**
   * 设置自定义sslcontext
   *
   * @param keyStorePath 密钥库路径
   * @param keyStorepass 密钥库密码
   */
  public HttpAsyncClientBuilderV2 ssl(String keyStorePath, String keyStorepass)
      throws HttpProcessException {
    this.ssls = SSLHelper.getInstance().customSSL(keyStorePath, keyStorepass);
    this.isNewSSL = true;
    return ssl();
  }


  /**
   * 设置连接池（默认开启https）
   *
   * @param maxTotal 最大连接数
   * @param defaultMaxPerRoute 每个路由默认连接数
   */
  public HttpAsyncClientBuilderV2 pool(int maxTotal, int defaultMaxPerRoute)
      throws HttpProcessException {
    if (isSetPool) {//如果已经设置过线程池，那肯定也就是https链接了
      return this;
    }
    // Create a registry of custom connection session strategies for supported
    // protocol schemes.
    Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
        .register("http", NoopIOSessionStrategy.INSTANCE)
        .register("https", ssls.getSSLIOSS(sslpv))
        .build();
    //配置io线程
    IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(12).build();
    //设置连接池大小
    ConnectingIOReactor ioReactor;
    try {
      ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
    } catch (IOReactorException e) {
      throw new HttpProcessException(e);
    }
    PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(
        ioReactor, null, sessionStrategyRegistry, null);
    connManager.setMaxTotal(maxTotal);
    connManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
    isSetPool = true;
    return (HttpAsyncClientBuilderV2) this.setConnectionManager(connManager);
  }

  /**
   * 设置代理
   *
   * @param hostOrIP 代理host或者ip
   * @param port 代理端口
   */
  public HttpAsyncClientBuilderV2 proxy(String hostOrIP, int port) {
    // 依次是代理地址，代理端口号，协议类型
    HttpHost proxy = new HttpHost(hostOrIP, port, "http");
    DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
    return (HttpAsyncClientBuilderV2) this.setRoutePlanner(routePlanner);
  }

  /**
   * 设置ssl版本<br>
   * 如果您想要设置ssl版本，必须<b>先调用此方法，再调用ssl方法<br>
   * 仅支持 SSLv3，TSLv1，TSLv1.1，TSLv1.2</b>
   */
  public HttpAsyncClientBuilderV2 sslpv(String sslpv) {
    return sslpv(SSLProtocolVersion.find(sslpv));
  }

  /**
   * 设置ssl版本<br>
   * 如果您想要设置ssl版本，必须<b>先调用此方法，再调用ssl方法<br>
   * 仅支持 SSLv3，TSLv1，TSLv1.1，TSLv1.2</b>
   */
  public HttpAsyncClientBuilderV2 sslpv(SSLProtocolVersion sslpv) {
    this.sslpv = sslpv;
    return this;
  }
}
