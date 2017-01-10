/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/6 13:48
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.connector;

import com.hikvision.bigdata.hbp.common.data.record.Record;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.Callback;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.ConnectorContext;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.ConnectorToKafka;
import com.hikvision.bigdata.hbp.datacollectors.common.data.record.RecordWrap;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * <p><kafka数据写入/p>
 *
 * @author chendongsheng5 2017/1/6 13:48
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/6 13:48
 * @modify by reason:{方法名}:{原因}
 */
public class DataGeneratorToKafka extends ConnectorToKafka {

	private final Properties props = new Properties();
	private String topic;
	private KafkaProducer<byte[], byte[]> producer = null;
	private Callback callback;

	public DataGeneratorToKafka(ConnectorContext context) {
		super(context);
//		this.props.putAll(PipelineUtils.createConnectorFromProps(context));
		this.topic = this.props.getProperty("topic");
		this.callback = getCallBack();
	}

	@Override
	public void open() throws IOException {
		producer = new KafkaProducer<>(props);
	}

	@Override
	protected void sendData(List<RecordWrap> readySend) {
		List<Record> records = new ArrayList<>();
		for(RecordWrap recordWrap : readySend) {
			records.add(recordWrap.getRecord());
		}
		put(records);
	}

	@Override
	public void flush() {
		producer.flush();
	}

	@Override
	public void close() throws IOException {
		if (producer != null) {
			producer.close();
		}
	}
}
