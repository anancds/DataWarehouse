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
import com.hikvision.mdp.commons.util.RandomUtils;

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

	//为了避免Unchecked assignment warning，所以用<?, ?>
	private static Map<?, ?> map;

	static {
		In in = new In(
				"mdp-datagenerator" + File.separator + "src" + File.separator + "main" + File.separator + "resources"
						+ File.separator + "CellPhoneTicket.json");

		JsonUtils jsonUtils = JsonUtils.nonDefaultMapper();
		map = jsonUtils.fromJson(in.readAll(), HashMap.class);
	}

	@SuppressWarnings("unchecked") private static <T> T cast(Object obj) {
		return (T) obj;
	}

	/**
	 * 获取运营商
	 *
	 * @return 运营商
	 */
	public static String getYYS() {
		List<String> data = cast(map.get("运营商"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取业务类型
	 *
	 * @return 业务类型
	 */
	public static String getYWLX() {
		List<String> data = cast(map.get("业务类型"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取服务号码
	 *
	 * @return 服务号码
	 */
	// TODO: 数值类型是long好，还是String好，need to be checked!
	public static long getFWHM() {
		List<String> data = cast(map.get("服务号码"));
		return Long.valueOf(data.get(RandomUtils.uniform(data.size())));
	}

	/**
	 * 获取卡号
	 *
	 * @return 卡号
	 */
	public static long getKH() {
		List<String> data = cast(map.get("卡号"));
		return Long.valueOf(data.get(RandomUtils.uniform(data.size())));
	}

	/**
	 * 获取设备号码
	 *
	 * @return 设备号码
	 */
	public static String getSBHM() {
		List<String> data = cast(map.get("设备号码"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取对方号码
	 *
	 * @return 对方号码
	 */
	public static long getDFHM() {
		List<String> data = cast(map.get("对方号码"));
		return Long.valueOf(data.get(RandomUtils.uniform(data.size())));
	}

	/**
	 * 获取对方号码归属地
	 *
	 * @return 对方号码归属地
	 */
	public static String getDFHMGSD() {
		List<String> data = cast(map.get("对方号码归属地"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取通话时长
	 *
	 * @return 通话时长
	 */
	public static int getTHSC() {
		List<String> data = cast(map.get("通话时长"));
		return Integer.valueOf(data.get(RandomUtils.uniform(data.size())));
	}

	/**
	 * 获取呼叫类型
	 *
	 * @return 呼叫类型
	 */
	public static String getHJLX() {
		List<String> data = cast(map.get("呼叫类型"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取LAC
	 *
	 * @return LAC
	 */
	public static String getLAC() {
		List<String> data = cast(map.get("LAC"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取CID
	 *
	 * @return CID
	 */
	public static String getCID() {
		List<String> data = cast(map.get("CID"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取服务号码基站
	 *
	 * @return 服务号码基站
	 */
	public static String getFWHMJZ() {
		List<String> data = cast(map.get("服务号码基站"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取MSC
	 *
	 * @return MSC
	 */
	public static String getMSC() {
		List<String> data = cast(map.get("MSC"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取城市
	 *
	 * @return 城市
	 */
	public static String getCS() {
		List<String> data = cast(map.get("城市"));
		return data.get(RandomUtils.uniform(data.size()));
	}

	/**
	 * 获取第三方号码
	 *
	 * @return 第三方号码
	 */
	public static long getDSFHM() {
		List<String> data = cast(map.get("第三方号码"));
		return Long.valueOf(data.get(RandomUtils.uniform(data.size())));
	}

	/**
	 * 获取第三方号码归属地
	 *
	 * @return 第三方号码归属地
	 */
	public static String getDSFHMGSD() {
		List<String> data = cast(map.get("第三方号码归属地"));
		return data.get(RandomUtils.uniform(data.size()));
	}
}
