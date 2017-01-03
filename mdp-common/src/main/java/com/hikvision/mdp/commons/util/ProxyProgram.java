/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/30 13:44
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>这个工具类用来动态调用各个main函数</p>
 *
 * @author chendongsheng5 2016/12/30 13:44
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/30 13:44
 * @modify by reason:{方法名}:{原因}
 */
public class ProxyProgram {

	private static final Logger logger = LogManager.getLogger(ProxyProgram.class);

	private Map<String, ProgramDescription> programs;

	public ProxyProgram() {

		programs = new TreeMap<>();
	}

	private static void printUsage(Map<String, ProgramDescription> programs) {
		logger.warn("Valid program name are: \r\n");
		for (Map.Entry<String, ProgramDescription> item : programs.entrySet()) {
			logger.info(" " + item.getKey() + ": " + item.getValue() + "\r\n");
		}
	}

	public void addClass(String name, Class mainClass, String description) throws NoSuchMethodException {
		programs.put(name, new ProgramDescription(mainClass, description));
	}

	public int run(String[] args) throws Throwable {
		int res = 0;

		if (0 == args.length) {
			logger.warn("You must give one argument.");
			printUsage(programs);
			res = -1;
		}

		ProgramDescription pgm = programs.get(args[0]);

		if (null == pgm) {
			logger.warn("Unknown Program [" + args[0] + "] chosen.");
			printUsage(programs);
			res = -1;
		}

		String[] newArgs = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			newArgs[i -1] = args[i];
		}
		pgm.invoke(newArgs);
		return res;
	}

	private static class ProgramDescription {
		static final Class<?>[] PARAM_TYPES = new Class<?>[] { String[].class };

		private Method main;

		private String description;

		public ProgramDescription(Class<?> mainClass, String description) throws NoSuchMethodException {
			this.main = mainClass.getMethod("main", PARAM_TYPES);
			this.description = description;
		}

		public void invoke(String[] args) throws Throwable {
			try {
				main.invoke(null, new Object[] { args });
			} catch (InvocationTargetException e) {
				throw e.getCause();
			}
		}

		public String getDescription() {
			return description;
		}
	}



}
