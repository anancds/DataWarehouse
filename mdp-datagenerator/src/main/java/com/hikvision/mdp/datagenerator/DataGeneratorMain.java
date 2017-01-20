/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/29 17:03
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.datagenerator;

import com.hikvision.mdp.commons.constants.BusinessType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>模拟数据生成入口类</p>
 *
 * @author chendongsheng5 2016/12/29 17:03
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/29 17:03
 * @modify by reason:{方法名}:{原因}
 */
public class DataGeneratorMain {

	private static final Logger LOG = LogManager.getLogger(DataGeneratorMain.class);

	public static void main(String[] args) {

		ParseArgument.processArgs(args, System.out);

		DataGeneratorFactory factory = new DataGeneratorFactory();
		DataToKafka dataToKafka = factory.getDataGenerator(BusinessType.values()[Integer.valueOf(DataGeneratorConstants.BUSINESS_TYPE)]);
		if (null == dataToKafka) {
			LOG.error("The Business Type is Wrong! Please input again!");
		} else {
			dataToKafka.sendData();
		}

	}
}
