/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/7 16:33
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hikvision.bigdata.hbp.datacollectors.common.data.schema.Schema;
import com.hikvision.mdp.commons.jackson.MapperType;
import com.hikvision.mdp.commons.jackson.ObjectMapperFactory;
import com.hikvision.mdp.commons.task.CollectorTask;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>Json</p>
 *
 * @author chendongsheng5 2017/1/7 16:33
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/7 16:33
 * @modify by reason:{方法名}:{原因}
 */
public class JSONParser implements IParser {

	@Override public List<Map<String, Object>> deserialize(ConsumerRecord<byte[], byte[]> consumerRecord, Schema schema)
			throws IOException {
		byte[] value = consumerRecord.value();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper(MapperType.JSON);
		// TODO: need to be checked!
		return mapper.readValue(value, List.class);
	}

	@Override public List<Map<String, Object>> deserialize(ConsumerRecord<byte[], byte[]> consumerRecord, Schema schema,
			CollectorTask collectorTask) throws IOException {
		throw new NotImplementedException("Method not implemented.");
	}
}
