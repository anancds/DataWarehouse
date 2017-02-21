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
import com.hikvision.mdp.EtlConstants;
import com.hikvision.mdp.commons.constants.business.BusinessType;
import com.hikvision.mdp.commons.jackson.MapperType;
import com.hikvision.mdp.commons.jackson.ObjectMapperFactory;
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

  // TODO: 类加载时是否会出错
  static {
    Class<?> clazz = BusinessType.values()[EtlConstants.business_name].getObj();
    try {
      ObjectWriter objectWriter = ObjectMapperFactory.getObjectWriter(MapperType.CSV, clazz);
      writer = objectWriter.writeValues(new File(EtlConstants.CSV_PATH));
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
  public static List<List<String>> getQueryResult(String accessTokenString,
      List<PostParameter> list)
      throws IopException, JSONException {
    //数据查询服务调用参数说明：(服务上下文, 服务版本, 请求方式, 请求参数)
    //返回结果为异步查询任务号时，该任务正在执行，可通过taskId 在aynQurey.java中查询结果或任务执行情况

    List<List<String>> data = new ArrayList<>();
    api.client.setToken(accessTokenString);//6.传递上一步获取到的访问令牌参数
    String context = BusinessType.values()[EtlConstants.business_name].getService_context();
    JSONObject result_get = api.sendCommand(context, EtlConstants.SERVICE_VERSION, "GET", list);
    JSONArray array = result_get.getJSONArray("data");
    for (int i = 0; i < array.length(); i++) {
      if (logger.isDebugEnabled()) {
        // TODO: 这样显示是否可以，需要调试
        logger.debug("The " + i + " data is: " + array.get(i).toString());
      }
      // TODO: checked!
      data.add((List<String>) ((HashMap) array.get(i)).values());
    }
    return data;
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
    long index;
    List<PostParameter> list;
    List<List<String>> result;
    for (index = 1; index < EtlConstants.TOTAL_INDEX; index++) {
      list = CommonUtils.getParam(String.valueOf(index), EtlConstants.PAGE_SIZE);

      result = CommonUtils.getQueryResult(accessTokenString, list);

      if (result.size() != 0) {
        CommonUtils.sendDataToCSV(result);
      } else {
        break;
      }
    }
  }

}
