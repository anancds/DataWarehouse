/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/7 14:34
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.connector;

import com.hikvision.bigdata.hbp.common.data.record.Record;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.ConnectorContext;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.ConvertorBase;
import com.hikvision.mdp.commons.kafka.Dataconvertor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.util.SerializationUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * <p>数据格式转换</p>
 *
 * @author chendongsheng5 2017/1/7 14:34
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/7 14:34
 * @modify by reason:{方法名}:{原因}
 */
@Deprecated
public class CommonConvertor extends ConvertorBase {

	// Configuration of pipeline
	protected final Properties props = new Properties();
	// The topic where data from
	protected String topicFrom;

	public CommonConvertor(ConnectorContext context) {
		super(context);
//		this.props.putAll(PipelineUtils.createConnectorFromProps(context));
		this.topicFrom = this.props.getProperty("topic");
	}

	@Override public ProducerRecord<byte[], byte[]> convert(Record record, String topic) throws IOException {
		if (null == record.getSchema()) {
			record.setSchema(getContext().getTopicSchema(topicFrom));
		}

		Map<String, Object> data = Dataconvertor.recordToMap(record);
		if (data.size() == 0) {
			return null;
		}
		// TODO: need to be checked 有可能确实需要先放入一个list
//		List<Map<String, Object>> list = new ArrayList<>();
//		list.add(data);
		return new ProducerRecord<>(topic, record.getKey(), SerializationUtils.serialize(data));
	}
}
