/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/9 16:20
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.httpclient.javahttp;

import com.hikvision.mdp.commons.exception.HttpProcessException;
import com.hikvision.mdp.commons.httpclient.common.SSLHelper;
import com.hikvision.mdp.commons.httpclient.common.SSLHelper.SSLProtocolVersion;
import java.io.IOException;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

/**
 * <p>Https工具类</p>
 * author chendongsheng5 2017/2/9 16:20
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/9 16:20
 * modify by reason:{方法名}:{原因}
 */
public class HttpsUtils extends CommonHttpUtils {

  @Override
  protected URLConnection createRequest(String strUrl, String strMethod)
      throws HttpProcessException {
    // 根据URL取得一个连接
    URLConnection conn = getRequestByUrl(strUrl);
    // 判断该连接是否 HTTPS 连接
    if (!(conn instanceof HttpsURLConnection)) {
      throw new HttpProcessException("This URL can not create HTTPS connection." + strUrl);
    }
    // 设置HTTPS相关配置
    try {
      HttpsURLConnection httpsConn = (HttpsURLConnection) conn;
      httpsConn.setRequestMethod(strMethod);
      httpsConn.setSSLSocketFactory(SSLHelper.getInstance().getSSLSF(SSLProtocolVersion.SSLv3));
      httpsConn.setHostnameVerifier(SSLHelper.getVerifier());
    } catch (IOException e) {
      throw new HttpProcessException(e.getMessage(), e);
    } catch (Exception e) {
      throw new HttpProcessException("Can not connect to the HTTPS server.", e);
    }

    return conn;
  }
}
