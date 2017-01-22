/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/22 15:14
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.config;

import com.hikvision.mdp.commons.constants.MDPConstants;
import com.hikvision.mdp.commons.util.PropertiesUtils;
import com.hikvision.mdp.commons.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * <p>mdp的配置文件解析</p>
 *
 * @author chendongsheng5 2017/1/22 15:14
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2017/1/22 15:14
 * @modify by reason:{方法名}:{原因}
 */
public class MdpConfig {

	private static final Logger LOG = LogManager.getLogger(MdpConfig.class);

	private static Properties mdpConfig = new Properties();

	static {
		getMdpPropConfig();
	}

	private static Properties getMdpPropConfig() {
		try {
			URL url = MdpConfig.class.getClassLoader().getResource(MDPConstants.CONFIG.CONFIG_MDP_NAME);
			if (null != url && null != url.toURI().getPath()) {
				mdpConfig = PropertiesUtils.getProperties(url.toURI().getPath(), true);
			} else {
				mdpConfig = PropertiesUtils.getProperties(MDPConstants.CONFIG.CONFIG_MDP_NAME, false);
			}
		} catch (URISyntaxException e) {
			LOG.error("MDP: load file {} failed so load jar file.", MDPConstants.CONFIG.CONFIG_MDP_NAME, e);
		}
		return mdpConfig;
	}

	/**
	 * 获取mdp properties的配置项
	 *
	 * @param key 配置属性
	 * @return 属性值
	 */
	public static String getPropConfigValue(String key) {
		String value = mdpConfig.getProperty(key);
		return StringUtils.hasText(value) ? value.trim() : null;
	}

	/**
	 * 获取配置项值，如果不存在，那么赋默认值
	 *
	 * @param key          配置属性
	 * @param defaultValue 默认值
	 * @return 如果属性值存在，那么返回，否则返回默认值
	 */
	public static String getPropConfigWithDefault(String key, String defaultValue) {
		String value = getPropConfigValue(key);
		return StringUtils.hasText(value) ? value : defaultValue;
	}

	/**
	 * Integer 配置项
	 *
	 * @param key          属性
	 * @param defaultValue 默认值
	 * @return int值
	 */
	public static int getPropConfigIntValue(String key, int defaultValue) {
		int res = defaultValue;
		try {
			String strValue = getPropConfigValue(key);
			if (StringUtils.hasText(strValue)) {
				res = Integer.parseInt(strValue);
			}
		} catch (Exception e) {
			LOG.error("Parse Integer to String failed.", e);
		}

		return res;
	}

}
