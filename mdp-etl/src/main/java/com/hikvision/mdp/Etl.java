/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/17 19:44
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp;

import com.alibaba.fastjson.JSON;
import com.hikvision.bigdata.hbp.common.data.record.Record;
import com.hikvision.bigdata.hbp.common.data.schema.Schema;
import com.hikvision.bigdata.hbp.common.utils.Bytes;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.source.ClientSourceConnector;
import com.hikvision.mdp.common.CommonUtils;
import com.hikvision.mdp.common.ParseETLArguments;
import com.hikvision.mdp.commons.constants.MDPConstants;
import com.hikvision.mdp.commons.exception.HttpProcessException;
import com.hikvision.mdp.commons.httpclient.MdpHttpClient;
import com.hikvision.mdp.commons.httpclient.common.HttpConfig;
import com.hikvision.mdp.commons.kafka.ConnectorPool;
import com.hikvision.mdp.commons.parser.YmlParse;
import com.hikvision.mdp.datagenerator.gen.CellPhoneTicketGen;
import iop.model.IopException;
import iop.org.json.JSONArray;
import iop.org.json.JSONException;
import iop.org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  private static Map<String, Schema> schemas = YmlParse.getSchemas();

  public static void main(String[] args)
      throws HttpProcessException, IOException, IopException, JSONException {

    ParseETLArguments.processArgs(args, System.out);
    ClientSourceConnector CellPhoneClient = ConnectorPool
        .getConnector(YmlParse.getKafkaAddress(MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA),
            YmlParse.getTopic(MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA, 0));

    if (EtlConstants.IS_DEBUG) {

      String result = MdpHttpClient.get(HttpConfig.custom().url("http://10.16.132.101:8080/service/hd/1.0"));
      JSONObject result_get = new JSONObject(result);
      System.out.println(result_get);
      System.out.println(result_get.getJSONObject("result").getJSONArray("data"));
      JSONArray array = result_get.getJSONObject("result").getJSONArray("data");

      List<Record> records = new ArrayList<>();
      Schema schema = schemas.get("hik_mdp_cellphone_schema");
      Record record = null;
      for (int i = 0; i < array.length(); i++) {
        JSONObject data = array.getJSONObject(i);
//        String rowKey = data.getString("qssj") + "_" + i;
        String rowKey = "1234" + "_" + i;
        record = new Record(Bytes.toBytes(rowKey), schema)
            .field("yys", data.getString("yys")).field("ywlx", data.getString("ywlx"))
            .field("qssj", data.getString("qssj")).field("fwhm", data.getString("fwhm"))
            .field("kh", data.getString("kh")).field("sbhm", data.getString("sbhm"))
            .field("dfhm", data.getString("dfhm"))
            .field("dfhmgsd", data.getString("dfhmgsd"))
            .field("thsc", data.getString("thsc")).field("hjlx", data.getString("hjlx"))
            .field("lac", data.getString("lac")).field("cid", data.getString("cid"))
            .field("fwhmjz", data.getString("fwhmjz"))
            .field("msc", data.getString("msc")).field("cs", data.getString("cs"))
            .field("dsfhm", data.getString("dsfhm"))
            .field("dsfhmgsd", data.getString("dsfhmgsd"));
        System.out.println(data.getString("dsfhmgsd"));

//        record.setTs(data.getLong("qssj"));
        record.setTs(4234234L);
        records.add(record);
      }
      CellPhoneClient.send(records);

    } else {
      String accessTokenString = CommonUtils.getAccessToken();//5.获取token字符串

      if (null == accessTokenString) {
        logger.error("The access token is null. so quit the program!");
        System.exit(1);
      }

      CommonUtils.sendData(accessTokenString);
    }


  }
}
