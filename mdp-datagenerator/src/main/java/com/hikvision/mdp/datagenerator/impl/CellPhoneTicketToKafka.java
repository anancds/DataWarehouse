/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/9 16:17
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.datagenerator.impl;

import com.hikvision.bigdata.hbp.common.data.record.Record;
import com.hikvision.bigdata.hbp.common.data.schema.Schema;
import com.hikvision.bigdata.hbp.common.utils.Bytes;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.source.ClientSourceConnector;
import com.hikvision.mdp.commons.constants.MDPConstants;
import com.hikvision.mdp.commons.kafka.ConnectorPool;
import com.hikvision.mdp.commons.parser.YmlParse;
import com.hikvision.mdp.commons.util.DateUtils;
import com.hikvision.mdp.commons.util.Stopwatch;
import com.hikvision.mdp.datagenerator.CellPhoneTicketGen;
import com.hikvision.mdp.datagenerator.DataGeneratorConstants;
import com.hikvision.mdp.datagenerator.DataToKafka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>话单数据发送到kafka</p>
 *
 * @author chendongsheng5 2017/1/9 16:17
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/9 16:17
 * @modify by reason:{方法名}:{原因}
 */
public class CellPhoneTicketToKafka implements DataToKafka, Closeable {

	private static final Logger LOG = LogManager.getLogger(CellPhoneTicketToKafka.class);

	private ExecutorService executorService = Executors.newFixedThreadPool(DataGeneratorConstants.THREAD_NUM);


	@Override public void sendData() {

		LOG.info("Begin to send CellPhone data to kafka!");

		Stopwatch stopwatch = new Stopwatch();

		ClientSourceConnector CellPhoneClient = ConnectorPool
				.getConnector(YmlParse.getKafkaAddress(MDPConstants.Collector.PIPELINE_INFO_HIK_SMART_METADATA),
						YmlParse.getTopic(MDPConstants.Collector.PIPELINE_INFO_HIK_SMART_METADATA, 0));


		executorService.submit(() -> {

		});

		Map<String, Schema> schemas = YmlParse.getSchemas();
		List<Record> records = new ArrayList<>();

		Schema schema = schemas.get("hik_mdp_cellphone_schema");
		String rowKey = "";

		int sendTimes = DataGeneratorConstants.TOTAL_NUM / DataGeneratorConstants.NUM_ONE_TIME;

		for (int i = 0; i < sendTimes; i++) {
			for (int j = 0; j < DataGeneratorConstants.NUM_ONE_TIME; j++) {
				long startTime = DateUtils
						.transStr2long(CellPhoneTicketGen.getQSSJ(), DateUtils.DEFAULT_DATE_All_FORMAT);
				Record record = new Record(Bytes.toBytes(rowKey), schema).field("YYS", CellPhoneTicketGen.getYYS())
						.field("YWLX", CellPhoneTicketGen.getYWLX()).field("QSSJ", CellPhoneTicketGen.getQSSJ())
						.field("FWHM", CellPhoneTicketGen.getFWHM()).field("KH", CellPhoneTicketGen.getKH())
						.field("SBHM", CellPhoneTicketGen.getSBHM()).field("DFHM", CellPhoneTicketGen.getDFHM())
						.field("DFHMGSD", CellPhoneTicketGen.getDFHMGSD()).field("THSC", CellPhoneTicketGen.getTHSC())
						.field("HJLX", CellPhoneTicketGen.getHJLX()).field("LAC", CellPhoneTicketGen.getLAC())
						.field("CID", CellPhoneTicketGen.getLAC()).field("FWHMJZ", CellPhoneTicketGen.getFWHMJZ())
						.field("MSC", CellPhoneTicketGen.getMSC()).field("CS", CellPhoneTicketGen.getCS())
						.field("DSFHM", CellPhoneTicketGen.getDSFHM())
						.field("DSFHMGSD", CellPhoneTicketGen.getDFHMGSD());
				record.setTs(startTime);
				records.add(record);
			}
			CellPhoneClient.send(records);
			records.clear();
		}

		ConnectorPool.close(YmlParse.getKafkaAddress(MDPConstants.Collector.PIPELINE_INFO_HIK_SMART_METADATA),
				YmlParse.getTopic(MDPConstants.Collector.PIPELINE_INFO_HIK_SMART_METADATA, 0));

		LOG.info("Send data to Kafka finished! Cost {} seconds!", stopwatch.elapsedTime());
	}

	@Override public void close() throws IOException {
		LOG.info("Close Data Generator.");

		if (null != executorService) {
			executorService.shutdown();
		}
	}
}
