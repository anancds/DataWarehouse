/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/22 11:17
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hikvision.mdp.commons.config.MdpConfig;
import com.hikvision.mdp.commons.constants.MDPConstants;
import org.apache.flume.Event;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>数据缓存类</p>
 *
 * @author chendongsheng5 2017/1/22 11:17
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/22 11:17
 * @modify by reason:{方法名}:{原因}
 */
public class EventCahce {

	private static EventCahce eventCahce = null;
	private static Cache<String, Event> cahce = null;
	private static byte[] lock = {};

	static {
		long msgEventCahceSize = Long.parseLong(MdpConfig
				.getPropConfigWithDefault(MDPConstants.CACHE.MSGEVENT_CAHCE_SIZE,
						MDPConstants.CACHE.DEFAULT_MSGEVENT_CAHCE_SIZE));
		eventCahce = EventCahce.getInstance(msgEventCahceSize, MDPConstants.CACHE.DEFAULT_MSG_TIMEOUT);
	}

	/**
	 * 创建一个新的实例msgCahce.
	 */
	private EventCahce(long size, long timeout) {
		init(size, timeout);
	}

	private static EventCahce getInstance(long size, long timeout) {
		if (eventCahce == null) {
			synchronized (lock) {
				if (eventCahce == null) {
					eventCahce = new EventCahce(size, timeout);
				}
			}
		}
		return eventCahce;
	}

	/**
	 * 单次插入数据
	 *
	 * @return
	 */
	public static synchronized boolean put(Event event) {
		if (null == event) {
			return false;
		}
		cahce.put(UUID.randomUUID().toString(), event);
		return true;
	}

	/**
	 * 批量插入数据
	 *
	 * @param eventList
	 */
	public static synchronized void putAll(List<Event> eventList) {
		if (CollectionUtils.isEmpty(eventList)) {
			return;
		}
		for (Event event : eventList) {
			if (!put(event)) {
				continue;
			}
		}
	}

	/**
	 * 一次取出cahce中的所有数据,并清空取出的数据
	 *
	 * @return
	 */
	public static synchronized List<Event> takeAll() {
		TreeMap<String, Event> map = new TreeMap<String, Event>(cahce.asMap());
		cahce.invalidateAll(map.keySet());
		return new ArrayList<Event>(map.values());
	}

	/**
	 * 根据key删除缓存中的消息
	 *
	 * @param key
	 */
	public static synchronized void removeBykey(String key) {
		cahce.asMap().remove(key);
	}

	/**
	 * 返回缓存大小
	 *
	 * @return
	 */
	public static synchronized long getSize() {
		return cahce.size();
	}

	private void init(long size, long timeout) {
		cahce = CacheBuilder.newBuilder().maximumSize(size).expireAfterWrite(timeout, TimeUnit.SECONDS).build();
	}
}
