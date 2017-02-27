/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/18 10:57
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.common;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.hikvision.bigdata.hbp.common.data.record.Record;
import com.hikvision.bigdata.hbp.common.data.schema.Schema;
import com.hikvision.bigdata.hbp.common.utils.Bytes;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.source.ClientSourceConnector;
import com.hikvision.mdp.EtlConstants;
import com.hikvision.mdp.commons.constants.MDPConstants;
import com.hikvision.mdp.commons.constants.business.BusinessType;
import com.hikvision.mdp.commons.jackson.MapperType;
import com.hikvision.mdp.commons.jackson.ObjectMapperFactory;
import com.hikvision.mdp.commons.kafka.ConnectorPool;
import com.hikvision.mdp.commons.parser.YmlParse;
import com.hikvision.mdp.datagenerator.gen.CellPhoneTicketGen;
import iop.Oauth;
import iop.OpenApi;
import iop.http.AccessToken;
import iop.model.IopException;
import iop.model.PostParameter;
import iop.org.json.JSONArray;
import iop.org.json.JSONException;
import iop.org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>爬数据的一些通用方法</p>
 * author chendongsheng5 2017/2/18 10:57
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/18 10:57
 * modify by reason:{方法名}:{原因}
 */
public class CommonUtils {

  private static final Logger logger = LogManager.getLogger(CommonUtils.class);

  private static SequenceWriter writer = null;
  private static OpenApi api = new OpenApi();//2.创建调用对象

  private static Map<String, Schema> schemas;

  private static ClientSourceConnector CellPhoneClient;

  // TODO: 类加载时是否会出错
  static {
    Class<?> clazz = BusinessType.values()[EtlConstants.BUSINESS_NAME].getObj();
    try {
      schemas = YmlParse.getSchemas();
      CellPhoneClient = ConnectorPool
          .getConnector(YmlParse.getKafkaAddress(MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA),
              YmlParse.getTopic(MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA, 0));
      ObjectWriter objectWriter = ObjectMapperFactory.getObjectWriter(MapperType.CSV, clazz);
      String path_csv =
          EtlConstants.PATH + clazz.getSimpleName() + "\\" + clazz.getSimpleName() + ".csv";
      writer = objectWriter.writeValues(new File(path_csv));
    } catch (IOException e) {
      logger.error("Get object writer failed!", e);
    }
  }

  /**
   * 获取oauth access tocken
   *
   * @return access token
   */
  public static String getAccessToken() {
    //1.在pae config.properties配置文件中做相应的配置
    Oauth oauth = new Oauth();//3.创建Oauth对象
    AccessToken token;//4.获取AccessToken
    String accessTokenString = null;
    try {
      token = oauth.getAccessTokenByCredentials();
      accessTokenString = token.getAccessToken();
      logger.warn("The access token is : " + accessTokenString);
    } catch (IopException e) {
      logger.error("Get the access token failed!", e);
    }

    return accessTokenString;
  }

  /**
   * 获取查询参数
   *
   * @param index 查询第几页
   * @param pageSize 每页显示数据量
   * @return 参数的list
   */
  public static List<PostParameter> getParam(String index, String pageSize) {
    List<PostParameter> list = new ArrayList<>();
    list.add(new PostParameter("index", index));
    list.add(new PostParameter("pagesize", pageSize));
    return list;
  }

  /**
   * @param accessTokenString oauth access token
   * @param list 查询参数列表
   * @return 数据集合，每条数据都是值的集合
   */
  @SuppressWarnings("unchecked")
  public static List<Record> getQueryResult(String accessTokenString,
      List<PostParameter> list)
      throws IopException, JSONException {
    //数据查询服务调用参数说明：(服务上下文, 服务版本, 请求方式, 请求参数)
    //返回结果为异步查询任务号时，该任务正在执行，可通过taskId 在aynQurey.java中查询结果或任务执行情况

    api.client.setToken(accessTokenString);//6.传递上一步获取到的访问令牌参数
    String context = BusinessType.values()[EtlConstants.BUSINESS_NAME].getService_context();
    JSONObject result_get = api.sendCommand(context, EtlConstants.SERVICE_VERSION, "GET", list);
    JSONArray array = result_get.getJSONObject("result").getJSONArray("data");

    List<Record> records = new ArrayList<>();
    Schema schema = schemas.get("hik_mdp_cellphone_schema");
    for (int i = 0; i < array.length(); i++) {
      JSONObject data = array.getJSONObject(i);
      String rowKey = data.get("qssj") + "_" + i;
      Record record = new Record(Bytes.toBytes(rowKey), schema)
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
      record.setTs(data.getLong("qssj"));
      records.add(record);
    }
    return records;
  }

  /**
   * 把从http请求中解析出数据发送到CSV文件中
   *
   * @param data 数据
   */
  public static void sendDataToCSV(List<List<String>> data) {
    for (int i = 0; i < data.size(); i++) {
      try {
        writer.write(data.get(i));
      } catch (IOException e) {
        logger.error("Write data to CSV file failed!", e);
      }
    }
  }

  public static void sendData(String accessTokenString) throws IopException, JSONException {
    //循环时需要判断获取的数据，如果数据的size > 0 才需要继续，否则退出。
    long index;
    List<PostParameter> list;
    List<Record> result;
    for (index = 1; index < EtlConstants.TOTAL_INDEX; index++) {
      list = CommonUtils.getParam(String.valueOf(index), EtlConstants.PAGE_SIZE);

      result = CommonUtils.getQueryResult(accessTokenString, list);

      if (result.size() != 0) {
        CellPhoneClient.send(result);//kafka
      } else {
        break;
      }
    }
  }

}
