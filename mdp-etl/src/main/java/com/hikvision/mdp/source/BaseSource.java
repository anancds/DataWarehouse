/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/22 10:50
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.source;

import com.hikvision.mdp.cache.EventCahce;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.EventDrivenSource;
import org.apache.flume.PollableSource;
import org.apache.flume.source.AbstractSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>初始化工作</p>
 *
 * @author chendongsheng5 2017/1/22 10:50
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/22 10:50
 * @modify by reason:{方法名}:{原因}
 */
public class BaseSource extends AbstractSource implements EventDrivenSource,PollableSource {

	private static final Logger LOG = LogManager.getLogger(BaseSource.class);

	@Override
	public void start() {
		super.start();
		// TODO: 一些初始化
	}
	@Override public Status process() throws EventDeliveryException {
		List<Event> eventList = EventCahce.takeAll();
		if(CollectionUtils.isEmpty(eventList)) {
			try {
				Thread.sleep(10000);//如果错误队列为空则休眠10秒钟
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return Status.BACKOFF;
		}
		getChannelProcessor().processEventBatch(eventList);
		return Status.READY;
	}

	@Override public long getBackOffSleepIncrement() {
		return 0;
	}

	@Override public long getMaxBackOffSleepInterval() {
		return 0;
	}
}
