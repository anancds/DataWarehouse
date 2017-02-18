/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/15 14:30
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp;

/**
 * <p>服务调用相关的常量</p>
 * author chendongsheng5 2017/2/15 14:30
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/15 14:30
 * modify by reason:{方法名}:{原因}
 */
public class EtlConstants {

  //服务地址
  public static final String URL = "http://10.154.207.161:80/ids-service/api";
  //服务版本
  public static final String SERVICE_VERSION = "1.0";
  // TODO: 确定查询是从第几页开始的，0还是1
  //查询第几页信息
  public static final long INDEX = 1;
  //每页显示的信息数
  public static final int PAGE_SIZE = 200;


  //有几个需要通过命令行获取的，csv路径和文件名字，业务类型，线程数，http请求暂停时间，服务版本号，服务地址，每页显示信息数
  //数据保存路径还需要根据业务类型用不同的目录来区分

  public static String CSV_PATH = "D:\\cds.csv";
  // TODO:  通过命令行获取
  public static int business_name = 1;

}
