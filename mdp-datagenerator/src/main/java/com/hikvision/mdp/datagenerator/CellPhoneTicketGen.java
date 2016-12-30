/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/29 16:27
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.datagenerator;

import com.hikvision.mdp.commons.util.In;
import com.hikvision.mdp.commons.util.JsonUtils;

import java.io.File;
import java.util.*;

/**
 * <p>话单数据生成</p>
 *
 * @author chendongsheng5 2016/12/29 16:27
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/29 16:27
 * @modify by reason:{方法名}:{原因}
 */
public class CellPhoneTicketGen {

	public static void main(String[] args) {

		In in = new In(
				"mdp-datagenerator" + File.separator + "src" + File.separator + "main" + File.separator + "resources"
						+ File.separator + "CellPhoneTicket.json");

		JsonUtils jsonUtils = JsonUtils.nonDefaultMapper();
		HashMap<String, Object> map;
		List<String> yunying;
		map = jsonUtils.fromJson(in.readAll(), HashMap.class);

		yunying = (List<String>) map.get("运营商");
		System.out.println(yunying.get(0));

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
