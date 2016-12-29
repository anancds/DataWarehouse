/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/29 20:01
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

import java.util.Collection;
import java.util.Map;

/**
 * <p>集合类库</p>
 *
 * @author chendongsheng5 2016/12/29 20:01
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/29 20:01
 * @modify by reason:{方法名}:{原因}
 */
public class CollectionUtils {

	/**
	 * 判断集合是否为空
	 *
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return (null == collection || collection.isEmpty());
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return (null == map || map.isEmpty());
	}
}
