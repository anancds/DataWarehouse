/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/6 17:56
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.task;

/**
 * <p>采集器任务抽象类</p>
 *
 * @author chendongsheng5 2017/1/6 17:56
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/6 17:56
 * @modify by reason:{方法名}:{原因}
 */
public abstract class CollectorTask<T> {
	/**
	 * 定义额外的工作
	 *
	 * @param t
	 */
	public abstract void doExtraJob(T t);
}
