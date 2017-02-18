/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/17 19:44
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp;

import com.hikvision.mdp.common.CommonUtils;
import com.hikvision.mdp.commons.exception.HttpProcessException;
import iop.model.IopException;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>爬取数据工具主函数类</p>
 *
 * @author chendongsheng5 2017/1/17 19:44
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/17 19:44
 * @modify by reason:{方法名}:{原因}
 */
// TODO:  如下

/**
 * 1、因为查询到第几页是不知道的，所以服务起来的时候从redis中读取每个服务的查询页，每次查询一页都需要把页数写入redis中。
 * 2、因为flume服务起来时会重新读取文件中的内容，所以在flume起来时需要重新命名监控的文件，防止数据重复读取，不过数据即使
 * 重复读取了其实也没关系。
 */
public class Etl {

  private static final Logger logger = LogManager.getLogger(Etl.class);

  public static void main(String[] args) throws HttpProcessException, IOException, IopException {

    String accessTokenString = CommonUtils.getAccessToken();//5.获取token字符串

    if (null == accessTokenString) {
      logger.error("The access token is null. so quit the program!");
      System.exit(1);
    }
    logger.warn("The access token is : " + accessTokenString);


    //循环时需要判断获取的数据，如果数据的size > 0 才需要继续，否则退出。

  }
}
