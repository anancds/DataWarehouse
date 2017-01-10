/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/9 15:58
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.datagenerator;

import com.hikvision.mdp.commons.constants.BusinessType;

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

	public DataGenerator getDataGenerator(BusinessType type) {
		if (null == type) {
			return null;
		}

		if (type.equals(BusinessType.HIGHWAY_VEHICLE)) {
			return new TelephoneBillGen();
		}

		return null;
	}
}
