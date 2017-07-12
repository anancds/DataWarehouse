/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/6 14:43
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.kafka;

import com.hikvision.bigdata.hbp.common.data.record.Record;
import com.hikvision.bigdata.hbp.common.data.schema.Field;
import com.hikvision.bigdata.hbp.common.data.schema.Schema;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.source.ClientSourceConnector;
import com.hikvision.mdp.commons.parser.YmlParse;
import org.apache.hadoop.hbase.util.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>kafka数据转换</p>
 *
 * @author chendongsheng5 2017/1/6 14:43
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/6 14:43
 * @modify by reason:{方法名}:{原因}
 */
public class Dataconvertor {

	private static final Logger LOG = LogManager.getLogger(Dataconvertor.class);

	/**
	 * 数据采集接口
	 *
	 * @param dataType
	 * @param data
	 */
	public static void storage(String kafkaAddr, String dataType, Map<String, Object> data, int topicNo) {
		if (data == null) {
			LOG.warn("Data should not be null.");
			return;
		}

		// 消息Schema
		Schema schema = YmlParse.getSchema(dataType);
		String topicName = YmlParse.getTopic(dataType, topicNo);

		List<Record> records = new ArrayList<>();
		// Get Connector
		ClientSourceConnector connector = ConnectorPool.getConnector(kafkaAddr, topicName);
		connector.connect();
		Record record = mapToRecord(data, schema);
		records.add(record);
		connector.send(records);
	}

	/**
	 * HBP数据批量采集接口
	 *
	 * @param dataType
	 * @param list
	 */
	public static void storageBatch(String kafkaAddr, String dataType, List<Map<String, Object>> list, int topicNo) {
		if (list == null) {
			LOG.warn("List should not be null.");
			return;
		}

		// 消息Schema
		Schema schema = YmlParse.getSchema(dataType);
		String topicName = YmlParse.getTopic(dataType, topicNo);

		List<Record> records = new ArrayList<>();
		// Get Connector
		ClientSourceConnector connector = ConnectorPool.getConnector(kafkaAddr, topicName);
		for (Map<String, Object> data : list) {
			Record record = mapToRecord(data, schema);
			records.add(record);
		}
		connector.send(records);
	}

	/**
	 * Map to Record
	 *
	 * @param map    需要转换的数据
	 * @param schema 需要转换的数据的schema
	 * @return kafka的record
	 */
	public static Record mapToRecord(Map<String, Object> map, Schema schema) {
		Record record = new Record(null, schema);
		for (Field field : schema.fields()) {
			try {
				switch (field.getType()) {
				case INT8: {
					Byte value = (Byte) map.get(field.getName());
					if (value != null) {
						record.field(field.getName(), value);
					}
					break;
				}
				case INT16: {
					Short value = (Short) map.get(field.getName());
					if (value != null) {
						record.field(field.getName(), value);
					}
					break;
				}
				case INT32: {
					Integer value = (Integer) map.get(field.getName());
					if (value != null) {
						record.field(field.getName(), value);
					}
					break;
				}
				case INT64: {
					Long value = (Long) map.get(field.getName());
					if (value != null) {
						record.field(field.getName(), value);
					}
					break;
				}
				case FLOAT32: {
					Object obj = map.get(field.getName());
					if (obj != null) {
						if (obj instanceof BigDecimal) {
							float value = ((BigDecimal) obj).floatValue();
							record.field(field.getName(), value);
						} else if (obj instanceof Integer) {
							record.field(field.getName(), ((Integer) obj).floatValue());
						} else {
							record.field(field.getName(), (Float) obj);
						}
					}
					break;
				}
				case FLOAT64: {
					Object obj = map.get(field.getName());
					if (obj != null) {
						if (obj instanceof BigDecimal) {
							double value = ((BigDecimal) obj).doubleValue();
							record.field(field.getName(), value);
						} else if (obj instanceof Integer) {
							record.field(field.getName(), ((Integer) obj).doubleValue());
						} else {
							double value = (Double) obj;
							record.field(field.getName(), value);
						}
					}
					break;
				}
				case BOOLEAN: {
					Boolean value = (Boolean) map.get(field.getName());
					if (value != null) {
						record.field(field.getName(), value);
					}
					break;
				}
				case STRING: {
					String value = null;
					Object valObj = map.get(field.getName());
					if (valObj != null) {
						value = map.get(field.getName()).toString();
					}
					if (value != null) {
						record.field(field.getName(), value);
					}
					break;
				}
				case BYTES: {
					Object obj = map.get(field.getName());
					if (obj != null) {
						if (obj instanceof String) {
							byte[] value = Base64.decode((String) obj);
							record.field(field.getName(), value);
						} else {
							byte[] value = (byte[]) obj;
							record.field(field.getName(), value);
						}
					}
					break;
				}
				default: {
					LOG.error("Unknown type of field: {}", field.getType());
					break;
				}
				}
			} catch (ClassCastException e) {
				LOG.error("Field " + field.getName() + " cast error!", e);
			}
		}
		return record;
	}

	/**
	 * kafka的record转map
	 *
	 * @param record kafka record
	 * @return 转换后的map
	 */
	public static Map<String, Object> recordToMap(Record record) {
		Schema schema = record.getSchema();
		Map<String, Object> map = new HashMap<>();

		for (Field field : schema.fields()) {
			Object value = record.fieldValue(field.getName());
			if (value != null) {
				if (value instanceof ByteBuffer) {
					map.put(field.getName(), ((ByteBuffer) value).array());
				} else {
					map.put(field.getName(), value);
				}
			}
		}
		return map;
	}
}
