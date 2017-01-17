/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/17 16:22
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

/**
 * <p>计时工具类</p>
 *
 * @author chendongsheng5 2017/1/17 16:22
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/17 16:22
 * @modify by reason:{方法名}:{原因}
 */
public class Stopwatch {

	private final long start;

	public Stopwatch() {
		start = System.currentTimeMillis();
	}

	/**
	 * 获取从Stopwatch创建开始的时间
	 *
	 * @return 经过的时间
	 */
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;
	}
}
