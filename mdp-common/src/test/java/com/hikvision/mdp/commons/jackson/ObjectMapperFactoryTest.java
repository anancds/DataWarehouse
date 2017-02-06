/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/7 17:00
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p></p>
 *
 * @author zhangsiwei6 2017/1/10 11:22
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: zhangsiwei6 2017/1/10 11:22
 * @modify by reason:{方法名}:{原因}
 */
public class ObjectMapperFactoryTest {
	private JsonGenerator jsonGenerator = null;
	private ObjectMapper objectMapper = null;
	private JavaObject bean = null;
	private ListToStrings listToStrings = null;

	@Before
	public void init() {
		bean = new JavaObject();
		bean.setAddress("HZ");
		bean.setEmail("111@gmail.com");
		bean.setId(1);
		bean.setName("xiaowei1");

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
			jsonGenerator = null;
			objectMapper = null;
			bean = null;
			System.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		assertThat("abc").isEqualTo("abc");
	}

	/**
	 * <b>function:</b>将java对象转换成json字符串
	 *
	 * @author zhangsiwei6
	 * @createDate 2017/1/10 下午15:01
	 */
	@Test
	public void writeEntityJSON() {
		try {
			//writeValue具有和writeObject相同的功能
			String result = objectMapper.writeValueAsString(bean);
			assertThat(result)
					.isEqualTo("{\"id\":1,\"name\":\"xiaowei1\",\"email\":\"111@gmail.com\",\"address\":\"HZ\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>function:</b>将map转换成json字符串
	 *
	 * @author zhangsiwei6
	 * @createDate 2017/1/10 下午15:20
	 */
	@Test
	public void writeMapJSON() {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put(bean.getName(), bean);

			bean = new JavaObject();
			bean.setId(2);
			bean.setName("xiaowei2");
			bean.setAddress("BJ");
			bean.setEmail("222@qq.com");
			map.put(bean.getName(), bean);

			String result = objectMapper.writeValueAsString(map);
			assertThat(result).isEqualTo(
					"{\"xiaowei1\":{\"id\":1,\"name\":\"xiaowei1\",\"email\":\"111@gmail.com\",\"address\":\"HZ\"},"
							+ "\"xiaowei2\":{\"id\":2,\"name\":\"xiaowei2\",\"email\":\"222@qq.com\",\"address\":\"BJ\"}}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>function:</b>将list集合转换成json字符串
	 *
	 * @author zhangsiwei6
	 * @createDate 2017/1/10 下午15:45
	 */
	@Test
	public void writeListJSON() {
		try {
			List<JavaObject> list = new ArrayList<>();
			list.add(bean);

			bean = new JavaObject();
			bean.setId(2);
			bean.setAddress("BJ");
			bean.setEmail("222@gmail.com");
			bean.setName("xiaowei2");
			list.add(bean);

			//用objectMapper直接返回list转换成的JSON字符串
			String result = objectMapper.writeValueAsString(list);
			assertThat(result).isEqualTo(
					"[{\"id\":1,\"name\":\"xiaowei1\",\"email\":\"111@gmail.com\",\"address\":\"HZ\"},"
							+ "{\"id\":2,\"name\":\"xiaowei2\",\"email\":\"222@gmail.com\",\"address\":\"BJ\"}]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void writeListMap() {
		try {
			List<Map<String, Object>> testList = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			int[] array1 = { 1, 1, 1 };
			int[] array2 = { 2, 2, 2 };
			int[] array3 = { 3, 3, 3 };
			map.put("001", array1);
			map.put("002", array2);
			map.put("003", array3);
			testList.add(map);
			String result = objectMapper.writeValueAsString(testList);
			assertThat(result).isEqualTo("[{\"001\":[1,1,1],\"002\":[2,2,2],\"003\":[3,3,3]}]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void genListMap() {
		try {
			List<Map<String, Object>> testList = new ArrayList<>();
			Map<String, Object> map1 = new HashMap<>();
			Map<String, Object> map2 = new HashMap<>();
			String[] map1_1 = { "移动", "联通" };
			String[] map1_2 = { "GPRS业务", "移动语音业务" };
			String map1_3 = "更详细准确的错误信息";
			map1.put("运营商", map1_1);
			map1.put("业务类型", map1_2);
			map1.put("Json格式验证", map1_3);
			testList.add(map1);

			String[] map2_1 = { "篮球", "足球", "排球" };
			String[] map2_2 = { "篮球色", "红色", "白色", "花色" };
			String map2_3 = "所有的大小都是一样的大";
			map2.put("球类", map2_1);
			map2.put("颜色", map2_2);
			map2.put("对大小的描述", map2_3);
			testList.add(map2);

			byte[] ListMap = objectMapper.writeValueAsBytes(testList);
			List<Map<String, Object>> result = objectMapper.readValue(ListMap, List.class);
			assertThat(result.size()).isEqualTo(testList.size());
			//String[] in testMap  |  ArrayList in resultMap
			int count = 0;
			for (Map<String, Object> testMap : testList) {
				Map<String, Object> resultMap = result.get(count);
				assertThat(testMap.size()).isEqualTo(resultMap.size());
				for (String key : testMap.keySet()) {
					assertThat(resultMap.containsKey(key));
					if (testMap.get(key).equals(resultMap.get(key))) {
						assertThat(testMap.get(key)).isEqualTo(resultMap.get(key));
					} else {
						for (int i = 0; i < (((String[]) testMap.get(key)).length); i++) {
							assertThat(((String[]) testMap.get(key))[i])
									.isEqualTo(((ArrayList) resultMap.get(key)).get(i));
						}
					}
				}
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * JSON TO JAVA
	 */
	@Test
	public void readJsonToListMap() {
		String json = "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}";
		try {
			Map<?, ?> bean = objectMapper.readValue(json, Map.class);
			assertThat(bean.size()).isEqualTo(4);
			assertThat(bean.get("address")).isEqualTo("address");
			assertThat(bean.get("name")).isEqualTo("haha");
			assertThat(bean.get("id")).isEqualTo(1);
			assertThat(bean.get("email")).isEqualTo("email");
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class JavaObject {
		private int id;
		private String name;
		private String email;
		private String address;

		public int getId() {
			return id;
		}

		private void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		private void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		private void setEmail(String email) {
			this.email = email;
		}

		public String getAddress() {
			return address;
		}

		private void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return this.name + "#" + this.id + "#" + this.address + "#" + this.email;
		}
	}

	class ListToStrings {

		private static final String SEP1 = "{";
		private static final String SEP2 = "}";
		private static final String SEP3 = "[";
		private static final String SEP4 = "]";
		private static final String SEP5 = ":";
		private static final String SEP6 = ",";

		private String ListToString(List<?> list) {
			StringBuilder sb = new StringBuilder();
			sb.append(SEP3);
			if (null != list && list.size() > 0) {
				for (Object obj : list) {
					if (null == obj || obj == "" || obj.equals("")) {
						continue;
					}
					// 如果值是list类型则调用自己
					if (obj instanceof List) {
						sb.append(ListToString((List<?>) obj));
					} else if (obj instanceof Map) {
						sb.append(SEP1 + MapToString((Map<?, ?>) obj) + SEP2);
					} else {
						sb.append(obj);
					}
				}
			}
			sb.append(SEP4);
			return sb.toString();
		}

		private String MapToString(Map<?, ?> map) {
			StringBuilder sb = new StringBuilder();
			// 遍历map
			for (Object key : map.keySet()) {
				if (null == key) {
					continue;
				}
				Object value = map.get(key);
				if (value instanceof List<?>) {
					sb.append(key.toString() + ListToString((List<?>) value));
				} else if (value instanceof Map<?, ?>) {
					sb.append(key.toString() + MapToString((Map<?, ?>) value));
				} else if (value instanceof String[]) {
					sb.append(key.toString() + SEP5);
					for (String str : (String[]) value) {
						sb.append(str);
					}
					sb.append(SEP6);
				} else {
					sb.append(key.toString() + value.toString());
				}
			}
			return sb.toString();
		}
	}
}
