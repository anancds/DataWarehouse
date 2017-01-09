/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/6 17:50
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.parser;

import com.hikvision.bigdata.hbp.common.data.schema.Schema;
import com.hikvision.mdp.commons.task.CollectorTask;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>kafka数据反序列化</p>
 *
 * @author chendongsheng5 2017/1/6 17:50
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/6 17:50
 * @modify by reason:{方法名}:{原因}
 */
public interface IParser {

	public List deserialize(ConsumerRecord<byte[], byte[]> consumerRecord, Schema schema)
			throws IOException;

	public List<Map<String, Object>> deserialize(ConsumerRecord<byte[], byte[]> consumerRecord, Schema schema,
			CollectorTask collectorTask) throws IOException;
}

