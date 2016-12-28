/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/27 15:20
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

import java.util.Random;

/**
 * <p>随机数生成器工具类</p>
 *
 * @author chendongsheng5 2016/12/27 15:20
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/27 15:20
 * @modify by reason:{方法名}:{原因}
 */
public final class RandomUtils {

	private static final char[] CHARS = new char[] { 'q', 'w', 'e', 'r', 't', 'z', 'u', 'i', 'o', 'p', 'a', 's', 'd',
			'f', 'g', 'h', 'j', 'k', 'l', 'y', 'x', 'c', 'v', 'b', 'n', 'm' };

	//伪随机数字生成器
	private static Random random;

	//伪随机数字生成器的种子
	private static long seed;

	static {
		seed = System.currentTimeMillis();
		random = new Random();
	}

	private RandomUtils() {

	}

	/**
	 * 获取种子
	 *
	 * @return the seed
	 */
	public static long getSeed() {
		return seed;
	}

	/**
	 * 重新设置种子
	 *
	 * @param s the seed
	 */
	public static void setSeed(long s) {
		seed = s;
		random = new Random(seed);
	}

	/**
	 * 返回一个均匀分布的随机数，范围是[0, 1)
	 *
	 * @return 均匀分布随机数
	 */
	public static double uniform() {
		return random.nextDouble();
	}

	/**
	 * 返回一个均匀分布的随机整数，范围是[0, n)
	 *
	 * @param n 整数范围
	 * @return 返回一个均匀分布是随机整数
	 */
	public static int uniform(int n) {
		if (n <= 0)
			throw new IllegalArgumentException("argument must be positive");
		return random.nextInt(n);
	}

	/**
	 * 返回均匀分布的随机数，范围是[a, b)
	 *
	 * @param a 最小值
	 * @param b 最大值
	 * @return 返回随机数
	 */
	public static int uniform(int a, int b) {
		if ((b <= a) || ((long) b - a >= Integer.MAX_VALUE)) {
			throw new IllegalArgumentException("invalid range: [" + a + ", " + b + "]");
		}

		return a + uniform(b - a);
	}

	public static double uniform(double a, double b) {
		if (!(a < b)) {
			throw new IllegalArgumentException("invalid range: [" + a + ", " + b + "]");
		}
		return a + uniform() * (b - a);
	}

	/**
	 * 返回随机的一个字符
	 *
	 * @return 随机字符
	 */
	public static char randomChar() {
		int len = uniform(0, CHARS.length);
		return CHARS[len];
	}

}
