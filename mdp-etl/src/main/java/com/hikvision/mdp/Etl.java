/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/17 19:44
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp;

import com.hikvision.mdp.commons.exception.HttpProcessException;
import com.hikvision.mdp.commons.httpclient.MdpHttpAsyncClient;
import com.hikvision.mdp.commons.httpclient.MdpHttpClient;
import com.hikvision.mdp.commons.httpclient.common.HttpConfig;
import com.hikvision.mdp.commons.httpclient.common.HttpHeader.Headers;
import iop.Oauth;
import iop.OpenApi;
import iop.http.AccessToken;
import iop.model.IopException;
import iop.model.PostParameter;
import iop.org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * <p></p>
 *
 * @author chendongsheng5 2017/1/17 19:44
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/17 19:44
 * @modify by reason:{方法名}:{原因}
 */
public class Etl {

  public static void main(String[] args) throws HttpProcessException, IOException, IopException {

    //1.在pae config.properties配置文件中做相应的配置
    OpenApi api = new OpenApi();//2.创建调用对象
    Oauth oauth = new Oauth();//3.创建Oauth对象
    AccessToken token = null;//4.获取AccessToken
    try {
      token = oauth.getAccessTokenByCredentials();
    } catch (IopException e) {
      e.printStackTrace();
    }
    String accessTokenString = token.getAccessToken();//5.获取token字符串
    System.out.println(accessTokenString);
    api.client.setToken(accessTokenString);//6.传递上一步获取到的访问令牌参数
    List<PostParameter> list = new ArrayList<>();// 组装业务参数（假如服务需要传递名称为cym与name两个参数）
    list.add(new PostParameter("GMSFHM","510202198212241237"));
    list.add(new PostParameter("index","1"));
    list.add(new PostParameter("pagesize","100"));

    //数据查询服务调用参数说明：(服务上下文, 服务版本, 请求方式, 请求参数)
    //返回结果为异步查询任务号时，该任务正在执行，可通过taskId 在aynQurey.java中查询结果或任务执行情况
    JSONObject result_get=api.sendCommand("/service/1049635", "2.0", "GET", list);//GET方式调用服务得到结果
    System.out.println(result_get.toString());
    JSONObject result_post=api.sendCommand("/service/1049635", "2.0", "POST", list);//POST调用服务得到结果
    System.out.println(result_post.toString());
  }
}
