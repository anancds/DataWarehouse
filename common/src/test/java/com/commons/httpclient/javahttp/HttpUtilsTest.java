/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/9 16:02
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.httpclient.javahttp;

import com.hikvision.mdp.commons.exception.HttpProcessException;
import org.junit.Test;

/**
 * <p>HTTP工具类测试</p>
 * author chendongsheng5 2017/2/9 16:02
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/9 16:02
 * modify by reason:{方法名}:{原因}
 */
public class HttpUtilsTest {

  @Test(expected = HttpProcessException.class)
  public void createRequest() throws Exception {

    String url = "https://www.facebook.com/";
    new HttpUtils().send("", url, null);
  }

}