package com.hikvision.mdp.commons.util;


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
