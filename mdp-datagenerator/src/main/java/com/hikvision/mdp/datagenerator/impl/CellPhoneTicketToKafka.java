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
import com.hikvision.bigdata.hbp.common.io.descriptor.Descriptors;
import com.hikvision.bigdata.hbp.common.io.descriptor.schema.SchemaDescriptor;
import com.hikvision.bigdata.hbp.common.utils.Bytes;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.builder.ConnectorBuilderV1;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.source.ClientSourceConnector;
import com.hikvision.bigdata.hbp.datacollectors.common.io.descriptors.DataCollectorPipelineDescriptor;
import com.hikvision.bigdata.hbp.datacollectors.common.io.descriptors.impl.DataCollectorPipelineDescriptorImpl;
import com.hikvision.mdp.commons.kafka.ConnectorPool;
import com.hikvision.mdp.commons.util.DateUtils;
import com.hikvision.mdp.datagenerator.CellPhoneTicketGen;
import com.hikvision.mdp.datagenerator.DataToKafka;
import javafx.scene.control.Cell;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>话单数据发送到kafka</p>
 *
 * @author chendongsheng5 2017/1/9 16:17
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/9 16:17
 * @modify by reason:{方法名}:{原因}
 */
public class CellPhoneTicketToKafka implements DataToKafka {

	static {

	}

	private String fileUrl =
			"mdp-common" + File.separator + "src" + File.separator + "main" + File.separator + "resources"
					+ File.separator + "schema" + File.separator + "hik_smart_metadata.yml";

	@Override public void sendData() {

		ClientSourceConnector CellPhoneClient = ConnectorPool.getConnector("", "");

		DataCollectorPipelineDescriptor descriptor = null;
		try {
			descriptor = Descriptors.parseFile(new File(fileUrl), DataCollectorPipelineDescriptorImpl.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<SchemaDescriptor> list = descriptor.schemas();
		Map<String, Schema> schemas = ConnectorBuilderV1.createSchemas(list);
		List<Record> records = new ArrayList<>();

		Schema schema = schemas.get("hik_mac_info");
		String rowKey = "";

		for (int i = 0; i < 100; i++) {
			long startTime = DateUtils.transStr2long(CellPhoneTicketGen.getQSSJ(), DateUtils.DEFAULT_DATE_All_FORMAT);
			Record record = new Record(Bytes.toBytes(rowKey), schema).field("YYS", CellPhoneTicketGen.getYYS())
					.field("YWLX", CellPhoneTicketGen.getYWLX()).field("QSSJ", CellPhoneTicketGen.getQSSJ())
					.field("FWHM", CellPhoneTicketGen.getFWHM()).field("KH", CellPhoneTicketGen.getKH())
					.field("SBHM", CellPhoneTicketGen.getSBHM()).field("DFHM", CellPhoneTicketGen.getDFHM())
					.field("DFHMGSD", CellPhoneTicketGen.getDFHMGSD()).field("THSC", CellPhoneTicketGen.getTHSC())
					.field("HJLX", CellPhoneTicketGen.getHJLX()).field("LAC", CellPhoneTicketGen.getLAC())
					.field("CID", CellPhoneTicketGen.getLAC()).field("FWHMJZ", CellPhoneTicketGen.getFWHMJZ())
					.field("MSC", CellPhoneTicketGen.getMSC()).field("CS", CellPhoneTicketGen.getCS())
					.field("DSFHM", CellPhoneTicketGen.getDSFHM()).field("DSFHMGSD", CellPhoneTicketGen.getDFHMGSD());
			record.setTs(startTime);
			records.add(record);
		}
		CellPhoneClient.send(records);
		records.clear();
	}
}
