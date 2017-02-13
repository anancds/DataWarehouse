/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/6 15:18
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.kafka;

import com.hikvision.bigdata.hbp.datacollectors.api.connector.Callback;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.source.ClientSourceConnector;
import com.hikvision.mdp.commons.annotation.ThreadSafe;
import com.hikvision.mdp.commons.constants.MDPConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>kafka连接池</p>
 *
 * @author chendongsheng5 2017/1/6 15:18
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/6 15:18
 * @modify by reason:{方法名}:{原因}
 */
@ThreadSafe
public class ConnectorPool {

  private static final Logger LOG = LogManager.getLogger(ConnectorPool.class);
  public static long succeeds = 0;
  private static Map<String, ClientSourceConnector> pool = new HashMap<>();

  /**
   * create kafaka consumer connector
   * 需要同步该方法，相当于一个单例模式
   *
   * @param kafkaAddr kafka ip
   * @param topicName kafka topic name
   * @return kafka client connector
   */
  public static synchronized ClientSourceConnector getConnector(String kafkaAddr,
      String topicName) {

    String key = getKey(kafkaAddr, topicName);
    if (pool.get(key) != null) {
      return pool.get(key);
    }

    Map<String, String> kafkaConfig = initKafkaConfig(kafkaAddr);
    // Create Connector
    ClientSourceConnector connector = new ClientSourceConnector(topicName, kafkaConfig,
        new Callback() {

          /**
           * 发送成功回调
           *
           * @param record data
           */
          @Override
          public void onSucceeded(Object record) {
            succeeds++;
          }

          /**
           * 发送失败回调
           *
           * @param record data
           * @param t 抛出的异常
           */
          @Override
          public void onFailed(Object record, Throwable t) {
            LOG.error("Message send failed!", t);
          }
        });

    if (!pool.containsKey(key)) {
      pool.put(key, connector);
      connector.connect();
    }

    return connector;
  }

  /**
   * Kafka配置初始化
   *
   * @return key-value类型的配置
   */
  private static Map<String, String> initKafkaConfig(String kafkaAddr) {

    Map<String, String> kafkaConfig = new HashMap<>();
    kafkaConfig.put("acks", "all");
    kafkaConfig.put("retries", "0");
    kafkaConfig.put("batch.size", "16384");
    kafkaConfig.put("linger.ms", "1");
    kafkaConfig.put("buffer.memory", "33554432");
    kafkaConfig.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
    kafkaConfig
        .put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

    if (StringUtils.isEmpty(kafkaAddr)) {
      LOG.error("{} must be configured first.", MDPConstants.Collector.BOOTSTRAP_SERVERS_NAME);
      throw new RuntimeException(
          MDPConstants.Collector.BOOTSTRAP_SERVERS_NAME + " must be configured first.");
    }
    kafkaConfig.put(MDPConstants.Collector.BOOTSTRAP_SERVERS_NAME, kafkaAddr);

    return kafkaConfig;
  }

  private static String getKey(String kafkaAddr, String topic) {
    return kafkaAddr + MDPConstants.Collector.UNDERLINE + topic;
  }

  public static synchronized void close(String kafkaAddr, String topicName) {
    String key = getKey(kafkaAddr, topicName);
    ClientSourceConnector connector;
    if (null != pool.get(key)) {
      connector = pool.get(key);
      try {
        connector.disconnect();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      pool.remove(key);
    }
  }
}
