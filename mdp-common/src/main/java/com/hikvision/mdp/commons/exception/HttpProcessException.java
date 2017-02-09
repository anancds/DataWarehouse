/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/9 15:42
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.exception;

/**
 * <p></p>
 * author chendongsheng5 2017/2/9 15:42
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/9 15:42
 * modify by reason:{方法名}:{原因}
 */
public class HttpProcessException extends Exception {

  private static final long serialVersionUID = -2749168865492921426L;

  public HttpProcessException(Exception e) {
    super(e);
  }

  /**
   * @param msg 错误信息
   */
  public HttpProcessException(String msg) {
    super(msg);
  }

  /**
   * @param message 错误信息
   * @param e Exception
   */
  public HttpProcessException(String message, Exception e) {
    super(message, e);
  }

}
