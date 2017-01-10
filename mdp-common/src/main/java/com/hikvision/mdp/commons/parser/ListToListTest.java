/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/9 16:38
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hikvision.bigdata.hbp.datacollectors.common.data.schema.Schema;
import com.hikvision.mdp.commons.jackson.MapperType;
import com.hikvision.mdp.commons.jackson.ObjectMapperFactory;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author zhangsiwei6 2017/1/9 16:38
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: zhangsiwei6 2017/1/9 16:38
 * @modify by reason:{方法名}:{原因}
 */
public class ListToListTest {
	public static void main(String[] args) {
		System.out.println("List to list test start.");
		List<Map<String,String>> test = new ArrayList<>();
		Map<String,String> map = new HashMap<>();
		map.put("map1","map1_line1");
		map.put("map1","map1_line2");
		test.add(map);
		ObjectMapper mapper;



	}
	public List<Map<String, Object>> deserialize(ConsumerRecord<byte[], byte[]> consumerRecord, Schema schema)
			throws IOException {
		byte[] value = consumerRecord.value();
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper(MapperType.JSON);
		// TODO: need to be checked!
		return mapper.readValue(value, List.class);
	}
}
