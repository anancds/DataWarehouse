/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/29 20:32
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>命令行工具类</p>
 *
 * @author chendongsheng5 2016/12/29 20:32
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/29 20:32
 * @modify by reason:{方法名}:{原因}
 */
public class CmdUtils {
	public static final String UTF8 = "UTF8";
	public static final String GBK = "GBK";
	private static final int OS_LINUX = 1;
	private static final int OS_Windows = 2;
	/**
	 * 系统类型
	 *
	 * @see #OS_LINUX
	 * @see #OS_Windows
	 */
	private static int os;

	static {
		String osName = System.getProperty("os.name");
		if (osName.contains("Windows")) {
			os = OS_Windows;
		} else {
			os = OS_LINUX;
		}
	}

	/**
	 * 异步执行(执行后不等待结果返回)
	 *
	 * @param cmd 控制台命令
	 * @throws IOException
	 */
	public static Process executeNoWait(String cmd) throws IOException {
		String[] cmds;
		if (OS_Windows == os) {
			cmds = new String[] { "cmd", "/c", cmd };
		} else {
			cmds = new String[] { "/bin/sh", "-c", cmd };
		}
		return Runtime.getRuntime().exec(cmds);
	}

	/**
	 * 阻塞执行 (执行后等待结果返回)
	 *
	 * @param cmd 控制台命令
	 * @return 控制台命令输出
	 * @throws Exception
	 */
	public static String execute(String cmd) throws Exception {
		String charset = OS_LINUX == os ? UTF8 : GBK;
		Process ps = executeNoWait(cmd);
		InputStreamReader isr = new InputStreamReader(ps.getInputStream(), charset);
		BufferedReader br = new BufferedReader(isr);
		InputStreamReader err = new InputStreamReader(ps.getErrorStream(), charset);
		BufferedReader ebr = new BufferedReader(err);
		StringBuilder resultBuff = new StringBuilder();
		String line;
		ps.waitFor();
		String result;
		while ((line = ebr.readLine()) != null) {
			resultBuff.append(line).append("\n");
		}
		if (StringUtils.hasText(resultBuff.toString())) {
			throw new Exception(resultBuff.toString());
		}
		resultBuff.setLength(0);
		while ((line = br.readLine()) != null) {
			resultBuff.append(line).append("\n");
		}
		result = resultBuff.toString();
		return result;
	}
}
