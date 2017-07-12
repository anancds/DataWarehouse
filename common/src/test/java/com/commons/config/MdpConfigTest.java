/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/22 16:53
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.config;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>MDP 配置的单元测试</p>
 *
 * @author chendongsheng5 2017/1/22 16:53
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/22 16:53
 * @modify by reason:{方法名}:{原因}
 */
public class MdpConfigTest {

	@Test public void getPropConfigValue() throws Exception {
		assertThat(MdpConfig.getPropConfigValue("abc")).isEqualTo(null);

	}

	@Test public void getPropConfigWithDefault() throws Exception {
		assertThat(MdpConfig.getPropConfigWithDefault("abc", "abc")).isEqualTo("abc");
	}

	@Test public void getPropConfigIntValue() throws Exception {
		assertThat(MdpConfig.getPropConfigIntValue("abc", 1)).isEqualTo(1);
	}

}