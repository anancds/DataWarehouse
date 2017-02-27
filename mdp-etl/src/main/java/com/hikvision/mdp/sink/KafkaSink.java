/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/20 10:26
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.sink;

import com.hikvision.bigdata.hbp.common.data.record.Record;
import com.hikvision.bigdata.hbp.common.data.schema.Schema;
import com.hikvision.bigdata.hbp.datacollectors.api.connector.impl.source.ClientSourceConnector;
import com.hikvision.mdp.commons.constants.MDPConstants;
import com.hikvision.mdp.commons.kafka.ConnectorPool;
import com.hikvision.mdp.commons.parser.YmlParse;
import com.hikvision.mdp.commons.util.ObjectByteConvertUtils;
import java.util.Map;
import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p></p>
 * author chendongsheng5 2017/2/20 10:26
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/20 10:26
 * modify by reason:{方法名}:{原因}
 */
public class KafkaSink extends AbstractSink implements Configurable {

  private static Logger LOG = LogManager.getLogger(KafkaSink.class);

  private static ClientSourceConnector client = ConnectorPool
      .getConnector(YmlParse.getKafkaAddress(MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA),
          YmlParse.getTopic(MDPConstants.Collector.PIPELINE_INFO_HIK_MDP_DATA, 0));
  private static Map<String, Schema> schemas = YmlParse.getSchemas();

  @Override
  public Status process() throws EventDeliveryException {
    Status result = Status.READY;
    Channel channel = getChannel();
    Transaction transaction = channel.getTransaction();
    Event event = null;
    LOG.debug("sink receive a message!");
    try {
      transaction.begin();
      event = channel.take();
      boolean flag = false;
      if (null != event) {
        Record record = (Record) ObjectByteConvertUtils.ByteToObject(event.getBody());
        Schema schema = schemas.get("hik_mdp_cellphone_schema");
        record.setSchema(schema);
//        record.setKey("".getBytes());
        flag = client.sendSync(record);
        if (flag) {
          transaction.commit();
        } else {
          transaction.rollback();
          try {
            Thread.sleep(30 * 1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

      } else {
        // No event found, request back-off semantics from the sink runner
        result = Status.BACKOFF;
        transaction.commit();
      }
    } catch (Exception ex) {
      transaction.rollback();
      try {
        Thread.sleep(30 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      ex.printStackTrace();
      LOG.error("Failed to log event: " + event + ex);
    } finally {
      transaction.close();
    }

    return result;
  }

  @Override
  public void configure(Context context) {

  }

  @Override
  public synchronized void start() {
    super.start();
  }

  @Override
  public synchronized void stop() {
    super.stop();
  }
}
