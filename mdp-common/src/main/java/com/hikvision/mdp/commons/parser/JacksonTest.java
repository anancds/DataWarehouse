/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/9 16:28
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.parser;

/**
 * <p></p>
 *
 * @author zhangsiwei6 2017/1/9 16:28
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: zhangsiwei6 2017/1/9 16:28
 * @modify by reason:{方法名}:{原因}
 */
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonEncoding;
//import org.codehaus.jackson.xml.XmlMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * <b>function:</b>Jackson 将java对象转换成JSON字符串，也可以将JSON字符串转换成java对象
 * jar-lib-version: jackson-all-1.6.2
 * jettison-1.0.1
 * @author hoojo
 * @createDate 2010-11-23 下午04:54:53
 * @file JacksonTest.java
 * @package com.hoo.test
 * @project Spring3
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class JacksonTest {
	private JsonGenerator jsonGenerator = null;
	private ObjectMapper objectMapper = null;
	private AccountBean bean = null;

	@Before
	public void init() {
		bean = new AccountBean();
		bean.setAddress("china-Guangzhou");
		bean.setEmail("hoojo_@126.com");
		bean.setId(1);
		bean.setName("hoojo");

		objectMapper = new ObjectMapper();
		try {
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void destory() {
		try {
			if (jsonGenerator != null) {
				jsonGenerator.flush();
			}
			if (!jsonGenerator.isClosed()) {
				jsonGenerator.close();
			}
			jsonGenerator = null;
			objectMapper = null;
			bean = null;
			System.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
