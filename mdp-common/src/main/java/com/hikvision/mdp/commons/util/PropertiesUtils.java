/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2016/12/29 9:55
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>属性配置工具类</p>
 *
 * @author chendongsheng5 2016/12/29 9:55
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: chendongsheng5 2016/12/29 9:55
 * @modify by reason:{方法名}:{原因}
 */
public class PropertiesUtils {

	private static final Logger logger = LogManager.getLogger(PropertiesUtils.class);

	/**
	 * 从系统属性文件中获取相应的值
	 *
	 * @param key key
	 * @return 返回value
	 */
	public static String key(String key) {
		return System.getProperty(key);
	}

	/**
	 * 根据Key读取Value
	 *
	 * @param filePath 属性文件
	 * @param key      需要读取的属性
	 * @param real     是否是真实路径
	 */
	public static String GetValueByKey(String filePath, String key, boolean real) {
		InputStream in = null;
		Properties pps = new Properties();
		String res = "";
		try {
			if (real) {
				in = new FileInputStream(filePath);
			} else {
				in = PropertiesUtils.class.getResourceAsStream("/" + filePath);
			}
			pps.load(in);
			res = pps.getProperty(key);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return res;
	}

	/**
	 * @param filePath     根据Key读取Value,如果没有，则赋默认值
	 * @param key          需要读取的属性
	 * @param defalutValue 如果没有读到，那么赋默认值
	 * @param real         是否是真实路径
	 * @return 属性值
	 */
	public static String GetDefalutValueByKey(String filePath, String key, String defalutValue, boolean real) {
		InputStream in = null;
		Properties pps = new Properties();
		String res = "";
		try {
			if (real) {
				in = new FileInputStream(filePath);
			} else {
				in = PropertiesUtils.class.getResourceAsStream("/" + filePath);
			}
			pps.load(in);
			res = StringUtils.hasText(pps.getProperty(key, "").trim()) ? pps.getProperty(key, "").trim() : defalutValue;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	public static Map<String, String> properties(InputStream in) {
		Map<String, String> map = new HashMap<>();
		Properties pps = new Properties();
		try {
			pps.load(in);
		} catch (IOException e) {
			logger.error("load properties error:" + e.getMessage());
		}
		Enumeration en = pps.propertyNames();
		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			map.put(strKey, strValue);
		}
		return map;
	}

	/**
	 * 读取配置文件
	 *
	 * @param filePath 配置文件路径
	 * @param real 是否是真实路径
	 * @return 配置文件的properties
	 */
	public static Properties getProperties(String filePath, boolean real) {
		Properties pro = new Properties();
		InputStream in = null;
		try {
			if (real) {
				in = new FileInputStream(filePath);
			} else {
				in = PropertiesUtils.class.getResourceAsStream("/" + filePath);
			}
			pro.load(in);
		} catch (Exception e) {
			logger.error("Read " + filePath + " IOException:", e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("Read " + filePath + " IOException:", e);
			}
		}
		return pro;
	}

	/**
	 * 读取Properties的全部信息
	 *
	 * @param filePath 读取的属性文件
	 * @return 返回所有的属性 key:value<>key:value
	 */
	public static Map<String, String> GetAllProperties(String filePath) throws IOException {
		Map<String, String> map = new HashMap<>();
		Properties pps = new Properties();
		try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
			return properties(in);
		} catch (IOException e) {
			logger.error("load properties error");
		}
		return map;
	}

	/**
	 * 写入Properties信息
	 *
	 * @param filePath 写入的属性文件
	 * @param pKey     属性名称
	 * @param pValue   属性值
	 */
	public static void WriteProperties(String filePath, String pKey, String pValue) throws IOException {
		Properties props = new Properties();

		props.load(new FileInputStream(filePath));
		// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
		// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream fos = new FileOutputStream(filePath);
		props.setProperty(pKey, pValue);
		// 以适合使用 load 方法加载到 Properties 表中的格式，
		// 将此 Properties 表中的属性列表（键和元素对）写入输出流
		props.store(fos, "Update '" + pKey + "' value");

	}
}
