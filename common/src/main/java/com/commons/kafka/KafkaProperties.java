/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/9 16:43
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.kafka;

/**
 * <p>kafka配置常量</p>
 *
 * @author chendongsheng5 2017/1/9 16:43
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/9 16:43
 * @modify by reason:{方法名}:{原因}
 */
public class KafkaProperties {

	//yml配置文件中的kafka.bootstrap.servers属性
	public static final String KAFKA_SERVER = "kafka.bootstrap.servers";

	//yml配置文件中的kafka.zookeeper.connector属性
	public static final String KAFKA_ZOOKEEPER = "kafka.zookeeper.connector";
}
