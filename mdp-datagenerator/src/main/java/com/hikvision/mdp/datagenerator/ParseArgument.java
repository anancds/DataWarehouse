/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/30 11:16
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.datagenerator;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * <p>解析命令行参数</p>
 *
 * @author chendongsheng5 2016/12/30 11:16
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/30 11:16
 * @modify by reason:{方法名}:{原因}
 */
public class ParseArgument {

	public static void processArgs(String[] args, final PrintStream out) {
		Options options = buildOptions();

		try {
			CommandLine cmd = parseCommandLine(options, args);
			if (cmd.hasOption('h')) {
				printHelp(out, options);
			} else if (cmd.hasOption('v')) {
				out.println(
						"Data Generator Version: " + "" + " JVM: " + System.getProperty("java.version") + " Vendor: "
								+ System.getProperty("java.vm.vendor") + " OS: " + System.getProperty("os.name"));
			} else {
				if (!process(cmd)){
					System.exit(1);
				}
			}
		} catch (ParseException e) {
			out.println("error: " + e.getMessage());
			printHelp(out, options);
		}
//		catch (IOException ioe) {
//			out.println("error: " + ioe.getMessage());
//		}
	}

	private static Options buildOptions() {
		return new Options();
	}

	private static boolean process(CommandLine line) {
		return true;
	}

	private static void printHelp(PrintStream out, Options options) {
		HelpFormatter formatter = new HelpFormatter();
		PrintWriter pw = new PrintWriter(out);

		formatter.printHelp(pw, 80, "Data Generator [options] [args]",
				"The Data Generator command line processor.\nOptions:", options, 2, 4, null, // footer
				false);

		pw.flush();
	}

	/**
	 * 解析命令行
	 *
	 * @param options the options parser
	 * @param args    命令行参数
	 * @return 解析后的命令行
	 * @throws ParseException 解析异常
	 */
	private static CommandLine parseCommandLine(Options options, String[] args) throws ParseException {
		CommandLineParser parser = new DefaultParser();
		return parser.parse(options, args, true);
	}
}