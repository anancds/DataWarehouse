/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/9 16:36
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.httpclient.common;

import com.hikvision.mdp.commons.exception.HttpProcessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
import org.apache.http.ssl.SSLContexts;

/**
 * <p>SSL帮助类</p>
 * author chendongsheng5 2017/2/9 16:36
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/9 16:36
 * modify by reason:{方法名}:{原因}
 */
public class SSLHelper {

  private static final SSLHandler simpleVerifier = new SSLHandler();
  private static SSLSocketFactory sslFactory;
  private static SSLConnectionSocketFactory sslConnFactory;
  private static SSLIOSessionStrategy sslIOSessionStrategy;
  private static SSLHelper sslutil = new SSLHelper();
  private SSLContext sc;

  public static SSLHelper getInstance() {
    return sslutil;
  }

  // 信任主机
  public static HostnameVerifier getVerifier() {
    return simpleVerifier;
  }

  public synchronized SSLSocketFactory getSSLSF(SSLProtocolVersion sslpv)
      throws HttpProcessException {
    if (sslFactory != null) {
      return sslFactory;
    }
    try {
      SSLContext sc = getSSLContext(sslpv);
      sc.init(null, new TrustManager[]{simpleVerifier}, null);
      sslFactory = sc.getSocketFactory();
    } catch (KeyManagementException e) {
      throw new HttpProcessException(e);
    }
    return sslFactory;
  }

  public synchronized SSLConnectionSocketFactory getSSLCONNSF(SSLProtocolVersion sslpv)
      throws HttpProcessException {
    if (sslConnFactory != null) {
      return sslConnFactory;
    }
    try {
      SSLContext sc = getSSLContext(sslpv);
      sc.init(null, new TrustManager[]{simpleVerifier}, new java.security.SecureRandom());
      sslConnFactory = new SSLConnectionSocketFactory(sc, simpleVerifier);
    } catch (KeyManagementException e) {
      throw new HttpProcessException(e);
    }
    return sslConnFactory;
  }

  public synchronized SSLIOSessionStrategy getSSLIOSS(SSLProtocolVersion sslpv)
      throws HttpProcessException {
    if (sslIOSessionStrategy != null) {
      return sslIOSessionStrategy;
    }
    try {
      SSLContext sc = getSSLContext(sslpv);
      sc.init(null, new TrustManager[]{simpleVerifier}, new java.security.SecureRandom());
      sslIOSessionStrategy = new SSLIOSessionStrategy(sc, simpleVerifier);
    } catch (KeyManagementException e) {
      throw new HttpProcessException(e);
    }
    return sslIOSessionStrategy;
  }

  public SSLHelper customSSL(String keyStorePath, String keyStorepass) throws HttpProcessException {
    FileInputStream instream = null;
    KeyStore trustStore = null;
    try {
      trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
      instream = new FileInputStream(new File(keyStorePath));
      trustStore.load(instream, keyStorepass.toCharArray());
      // 相信自己的CA和所有自签名的证书
      sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
          .build();
    } catch (KeyManagementException | KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
      throw new HttpProcessException(e);
    } finally {
      try {
        instream.close();
      } catch (IOException e) {
      }
    }
    return this;
  }

  public SSLContext getSSLContext(SSLProtocolVersion sslpv) throws HttpProcessException {
    try {
      if (sc == null) {
        sc = SSLContext.getInstance(sslpv.getName());
      }
      return sc;
    } catch (NoSuchAlgorithmException e) {
      throw new HttpProcessException(e);
    }
  }

  /**
   * The SSL protocol version (SSLv3, TLSv1, TLSv1.1, TLSv1.2)
   */
  public static enum SSLProtocolVersion {
    SSL("SSL"),
    SSLv3("SSLv3"),
    TLSv1("TLSv1"),
    TLSv1_1("TLSv1.1"),
    TLSv1_2("TLSv1.2"),;
    private String name;

    private SSLProtocolVersion(String name) {
      this.name = name;
    }

    public static SSLProtocolVersion find(String name) {
      for (SSLProtocolVersion pv : SSLProtocolVersion.values()) {
        if (pv.getName().toUpperCase().equals(name.toUpperCase())) {
          return pv;
        }
      }
      throw new RuntimeException("The SSL version is not supported：" + name);
    }

    public String getName() {
      return this.name;
    }

  }

  // 重写X509TrustManager类的三个方法,信任服务器证书
  private static class SSLHandler implements X509TrustManager, HostnameVerifier {

    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
      return new java.security.cert.X509Certificate[]{};
      //return null;
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
        String authType) throws java.security.cert.CertificateException {
    }

    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
        String authType) throws java.security.cert.CertificateException {
    }

    @Override
    public boolean verify(String paramString, SSLSession paramSSLSession) {
      return true;
    }
  }


}
