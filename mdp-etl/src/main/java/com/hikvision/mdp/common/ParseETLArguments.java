/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/21 10:29
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.common;

import static org.apache.commons.cli.Option.builder;

import com.hikvision.mdp.EtlConstants;
import java.io.PrintStream;
import java.io.PrintWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * <p>解析ETL相关的命令行参数</p>
 * author chendongsheng5 2017/2/21 10:29
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/21 10:29
 * modify by reason:{方法名}:{原因}
 */
public class ParseETLArguments {


  public static void processArgs(String[] args, final PrintStream out) {
    Options options = buildOptions();

    try {
      CommandLine cmd = parseCommandLine(options, args);
      if (cmd.hasOption('h')) {
        printHelp(out, options);
      } else if (cmd.hasOption('v')) {
        out.println(
            "ETL Tools Version: " + "" + " JVM: " + System.getProperty("java.version")
                + " Vendor: "
                + System.getProperty("java.vm.vendor") + " OS: " + System.getProperty("os.name"));
      } else {
        if (!process(cmd)) {
          System.exit(1);
        }
      }
    } catch (ParseException e) {
      out.println("error: " + e.getMessage());
      printHelp(out, options);
    }
  }


  private static Options buildOptions() {
    return new Options()
        .addOption(builder("b").hasArg().argName("BusinessType")
            .desc("The Business Type of Data Generator").build())
        .addOption(builder("n").hasArg().argName("NumberOfThread")
            .desc("The Number of Thread to generator data!").build())
        .addOption(builder("h").hasArg(false).desc("usage information").longOpt("help").build())
        .addOption(
            builder("v").hasArg(false).desc("display Data Generator version and jvm version!")
                .build())
        .addOption(
            builder("p").hasArg(false).argName("Path").desc("The path used to save data!").build())
        .addOption(builder("t").hasArg(false).argName("TotalNumber").desc("Total number of indexes")
            .build())
        .addOption(builder("i").hasArg(false).argName("IntervalTime")
            .desc("The Interval time between every http request").build())
        .addOption(
            builder("s").hasArg(false).argName("PageSize").desc("Page Size of result").build())
        .addOption(builder("S").hasArg(false).argName("ServerVersion")
            .desc("The version of server,1.0 or 2.0").build())
        .addOption(
            builder("u").hasArg(false).argName("URL").desc("The URL of the server!").build());
  }

  private static boolean process(CommandLine line) {
    if (line.hasOption('b')) {
      EtlConstants.business_name = Integer.valueOf(line.getOptionValue('b'));
    }

    if (line.hasOption('t')) {
      EtlConstants.TOTAL_INDEX = Long.valueOf(line.getOptionValue('t'));
    }

    if (line.hasOption('i')) {
      EtlConstants.INTERVAL_TIME = Long.valueOf(line.getOptionValue('t'));
    }

    if (line.hasOption('s')) {
      EtlConstants.PAGE_SIZE = line.getOptionValue('s');
    }

    if (line.hasOption('S')) {
      EtlConstants.SERVICE_VERSION = line.getOptionValue('S');
    }

    if (line.hasOption('u')) {
      EtlConstants.URL = line.getOptionValue('u');
    }

    return true;
  }

  private static void printHelp(PrintStream out, Options options) {
    HelpFormatter formatter = new HelpFormatter();
    PrintWriter pw = new PrintWriter(out);

    formatter.printHelp(pw, 80, "ETL tools  [options] [args]",
        "The ETL tools command line processor.\nOptions:", options, 2, 4, null, // footer
        false);

    pw.flush();
  }

  /**
   * 解析命令行
   *
   * @param options the options parser
   * @param args 命令行参数
   * @return 解析后的命令行
   * @throws ParseException 解析异常
   */
  private static CommandLine parseCommandLine(Options options, String[] args)
      throws ParseException {
    CommandLineParser parser = new DefaultParser();
    return parser.parse(options, args, true);
  }
}
