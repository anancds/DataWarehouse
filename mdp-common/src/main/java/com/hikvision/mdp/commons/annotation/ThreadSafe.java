/*
 * @ProjectName: MDP
 * @Copyright: 2017 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/2/10 10:45
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>对线性安全类的注解</p>
 * author chendongsheng5 2017/2/10 10:45
 * version V1.0
 * modificationHistory =========================逻辑或功能性重大变更记录
 * modify by user: chendongsheng5 2017/2/10 10:45
 * modify by reason:{方法名}:{原因}
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS) // The original version used RUNTIME
public @interface ThreadSafe {
  // TODO: 需要判断多线程环境下运行的类是否是线程安全
}
