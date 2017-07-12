/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/17 15:22
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.parser;

import com.hikvision.bigdata.hbp.common.data.schema.Schema;
import com.hikvision.bigdata.hbp.common.io.descriptor.Descriptors;
import com.hikvision.bigdata.hbp.common.io.descriptor.schema.SchemaDescriptor;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.builder.ConnectorBuilderV1;
import com.hikvision.bigdata.hbp.datacollectors.common.io.descriptors.DataCollectorPipelineDescriptor;
import com.hikvision.bigdata.hbp.datacollectors.common.io.descriptors.impl.DataCollectorPipelineDescriptorImpl;
import com.hikvision.mdp.commons.constants.MDPConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>yml配置文件解析，支持多个yml配置文件</p>
 *
 * @author chendongsheng5 2017/1/17 15:22
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/17 15:22
 * @modify by reason:{方法名}:{原因}
 */
public class YmlParse {

	private static final Logger LOG = LogManager.getLogger(YmlParse.class);

	// TODO: fileUrl应该自动去获取
	private static String fileUrl = MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA;

	private static final String[] YMLFiles = new String[] { fileUrl };

	//key 是文件名
	private static Map<String, DataCollectorPipelineDescriptor> pipelineCache = new HashMap<>();
	private static Map<String, Schema> schemas = new HashMap<>();

	static {

		// 加载YML采集流水线配置
		for (String fileName : YMLFiles) {
			DataCollectorPipelineDescriptor descriptor = loadYMLResource(fileName);
			if (null != descriptor) {
				pipelineCache.put(fileName, descriptor);
				List<SchemaDescriptor> list = descriptor.schemas();
				Map<String, Schema> schemaMap = ConnectorBuilderV1.createSchemas(list);
				schemas.putAll(schemaMap);
			}
		}
	}

	/**
	 * 从YML加载数据采集配置
	 *
	 * @param ymlFile yml文件名
	 * @return DataCollectorPipelineDetail
	 */
	public static DataCollectorPipelineDescriptor loadYMLResource(String ymlFile) {
		LOG.info("Loading collector config from {} ...", ymlFile);
		// 解析Pipeline描述文件
		String newFile = "mdp-common" + File.separator + "src" + File.separator + "main" + File.separator + "resources"
				+ File.separator + "schema" + File.separator + ymlFile;
		File hik_mdp_data = new File(newFile);
		//文件合法性
		if (hik_mdp_data.isFile()) {
			try {
				return Descriptors.parseFile(hik_mdp_data, DataCollectorPipelineDescriptorImpl.class);
			} catch (IOException e) {
				LOG.error("IO Error while read YML file.", e);
			}
		}
		LOG.info("File didn't exist.");
		return null;
	}

	/**
	 * 获取数据解析Schema
	 *
	 * @param key schema的名字
	 * @return 对于的schema
	 */
	public static Schema getSchema(String key) {
		return schemas.get(key);
	}

	/**
	 * 获取全部的schemas
	 *
	 * @return schemas
	 */
	public static Map<String, Schema> getSchemas() {
		return schemas;
	}

	/**
	 * 获取数据发送的Kafka topic
	 *
	 * @param key yml文件名
	 * @return topic名字
	 */
	public static String getTopic(String key, int topicNo) {
		return pipelineCache.get(key).topics().get(topicNo).name();
	}

	/**
	 * 获取kafka地址
	 *
	 * @param key yml文件名
	 * @return kafka地址
	 */
	public static String getKafkaAddress(String key) {
		return pipelineCache.get(key).properties().getString("kafka.bootstrap.servers");
	}

	public static void main(String[] args) {

		System.out.println(getSchema("hik_smart_metadata_schema"));
		System.out.println(getTopic(MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA, 0));
		System.out.println(getKafkaAddress(MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA));
	}
}
