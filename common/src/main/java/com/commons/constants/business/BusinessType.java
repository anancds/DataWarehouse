/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/9 16:07
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.constants.business;

/**
 * <p>业务类型枚举类</p>
 *
 * @author chendongsheng5 2017/1/9 16:07
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/9 16:07
 * @modify by reason:{方法名}:{原因}
 */
public enum BusinessType {

  // TODO: 对每一个业务都需要有中文的注释
  HIGHWAY_VEHICLE(0, "/service/1163317", HighwayVehicles.class),
  RAILWAY_SECURITY_CHECK(1, "", Object.class),
  TEMPORARY_RESIDENT(2, "", Object.class),
  CELL_PHONE(3, "", Cellphone.class);

  Class<?> obj;
  private int code;
  //服务上下文
  private String service_context;

  BusinessType(int code, String service_context, Class<?> obj) {
    this.code = code;
    this.service_context = service_context;
    this.obj = obj;
  }

  public Class<?> getObj() {
    return obj;
  }

  public void setObj(Class<?> obj) {
    this.obj = obj;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getService_context() {
    return service_context;
  }

  public void setService_context(String service_context) {
    this.service_context = service_context;
  }

}
