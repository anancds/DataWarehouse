/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/3 19:43
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.datagenerator;

/**
 * <p>数据生成器的常量</p>
 *
 * @author chendongsheng5 2017/1/3 19:43
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/3 19:43
 * @modify by reason:{方法名}:{原因}
 */
public class DataGeneratorConstants {

	//服务名称，或者说是表名
	public static String SERVER_NAME = "";

	//生成数据的线程数
	public static int THREAD_NUM = 1;

	//数据的终点，0表示ES，1表示Hbase，2表示ES和Hbase，默认值是2。
	public static int DESTINATION = 2;

	//发送数据总条数
	public static int TOTAL_NUM = 0;

	//一次发送数据条数
	public static int NUM_ONE_TIME = 0;
}
