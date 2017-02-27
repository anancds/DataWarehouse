/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/6 17:09
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.connector;

import com.hikvision.bigdata.hbp.common.data.schema.Schema;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.ConnectorContext;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.ConnectorFromKafka;
import com.hikvision.mdp.commons.parser.IParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

/**
 * <p>kafka消费数据</p>
 *
 * @author chendongsheng5 2017/1/6 17:09
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/6 17:09
 * @modify by reason:{方法名}:{原因}
 */
@Deprecated
public class DataGeneratorFromKafka extends ConnectorFromKafka{

	private static Logger LOG = LogManager.getLogger(DataGeneratorFromKafka.class);

	protected final Properties props = new Properties();
	protected String topic;
	protected long pollTimeout;
	protected Schema schema;
	protected IParser parser;
	public DataGeneratorFromKafka(ConnectorContext context) {
		super(context);
	}
}
