/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/7 16:12
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.connector;

import com.hikvision.bigdata.hbp.datacollectors.api.connector.Callback;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.ConnectorContext;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.ConnectorFrom;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.ConnectorTo;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.sink.ConnectorToHBase;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.sink.HBaseSinkConnector;
import com.hikvision.bigdata.hbp.datacollectors.common.io.descriptors.DataCollectorPipelineDescriptor;

/**
 * <p>消费kafka数据到Hbase</p>
 *
 * @author chendongsheng5 2017/1/7 16:12
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/7 16:12
 * @modify by reason:{方法名}:{原因}
 */
@Deprecated
public class MDPHbaseSinkConnector extends HBaseSinkConnector {

	public MDPHbaseSinkConnector(DataCollectorPipelineDescriptor descriptor, String connectorName, int index,
			Callback toCallback) {
		super(descriptor, connectorName, index, toCallback);
	}

	@Override
	protected ConnectorFrom createConnectorFrom(ConnectorContext context) {
		return new DataGeneratorFromKafka(context);
	}

	@Override
	protected ConnectorTo createConnectorTo(ConnectorContext context) {
		return new ConnectorToHBase(context);
	}
}
