/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/23 16:19
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.kafka.config;

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

/**
 * <p>kafka配置列</p>
 *
 * @author chendongsheng5 2017/1/23 16:19
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/23 16:19
 * @modify by reason:{方法名}:{原因}
 */
public class Configuration implements Serializable{
	private static final long serialVersionUID = 1L;

	public final static String PRODUCER_TYPE = "producer.type";
	public final static String SERIALIZER_CLASS = "serializer.class";
	public final static String BOOTSTRAP_SERVERS = "bootstrap.servers";
	public final static String ZOOKEEPER_CONNECT = "zookeeper.connect";
	public final static String GROUP_ID = "group.id";
	public final static String ZOOKEEPER_SYNC_TIME_MS = "zookeeper.sync.time.ms";
	public final static String ZOOKEEPER_SESSION_TIMEOUT_MS = "zookeeper.session.timeout.ms";
	public final static String AUTO_COMMIT_ENABLE = "auto.commit.enable";
	public final static String AUTO_COMMIT_INTERVAL_MS = "auto.commit.interval.ms";
	public final static String FETCH_MESSAGE_MAX_BYTES = "fetch.message.max.bytes";
	public final static String BATCH_SIZE = "batch.size";
	public final static String SUBMIT_TIMEOUT = "submit.timeout";
	public final static String AUTO_OFFSET_RESET = "auto.offset.reset";
	public final static String REQUEST_REQUIRED_ACKS = "request.required.acks";

	public static final String MAX_REQUEST_SIZE = "max.request.size";
	public static final String COSUMER_KEY = "cosumer.key";

	private Properties _properties;

	public Configuration() {
		_properties = new Properties();
	}

	public Configuration(Map<String, String> properties) {
		_properties = new Properties();
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			_properties.put(entry.getKey(), entry.getValue());
		}
	}

	public Configuration(Properties properties) {
		_properties = properties;
	}

	/**
	 * 添加配置信息
	 *
	 * @param key 配置属性
	 * @param value 配置属性值
	 */
	public void addProperty(String key, String value) {
		_properties.put(key, value);
	}

	/**
	 * 获取所有配置信息
	 *
	 * @return 配置属性
	 */
	public Properties getConfig() {
		return _properties;
	}

	/**
	 * 根据key获取配置信息
	 *
	 * @param key 配置属性
	 * @return 配置属性值
	 */
	public String getProperty(String key) {
		return (_properties.get(key) != null) ? (String) _properties.get(key) : null;
	}
}
