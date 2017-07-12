/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/9 15:58
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.datagenerator;

import com.hikvision.mdp.commons.constants.business.BusinessType;
import com.hikvision.mdp.datagenerator.impl.CellPhoneTicketToKafka;

/**
 * <p>数据生成的工厂类</p>
 *
 * @author chendongsheng5 2017/1/9 15:58
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/9 15:58
 * @modify by reason:{方法名}:{原因}
 */
public class DataGeneratorFactory {

	public DataToKafka getDataGenerator(BusinessType type) {
		if (null == type) {
			return null;
		}

		DataToKafka res = null;

		// TODO: 当业务多的话，这个switch语句太长了，能否优化
		// 将使用频率高的case放在靠前的位置 或者 将type分类 再switch
		// 试试map
		switch (type) {
		case CELL_PHONE:
			res = new CellPhoneTicketToKafka();
			break;
		case HIGHWAY_VEHICLE:
			break;
		}

		return res;
	}
}
