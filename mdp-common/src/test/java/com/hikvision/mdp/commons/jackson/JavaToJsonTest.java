/*
 * @ProjectName: MDP
 * @Copyright: 2016 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * @address: http://www.hikvision.com
 * @date: 2017/1/10 11:22
 * @Description: 本内容仅限于杭州海康威视数字技术股份有限公司内部使用，禁止转发.
 */
package com.hikvision.mdp.commons.jackson;

/**
 * <p></p>
 *
 * @author zhangsiwei6 2017/1/10 11:22
 * @version V1.0
 * @modificationHistory =========================逻辑或功能性重大变更记录
 * @modify by user: zhangsiwei6 2017/1/10 11:22
 * @modify by reason:{方法名}:{原因}
 */

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>function:</b>Jackson 将java对象转换成JSON字符串，也可以将JSON字符串转换成java对象
 */
public class JavaToJsonTest{
	private JsonGenerator jsonGenerator = null;
	private ObjectMapper objectMapper = null;
	private JavaObject bean = null;

	class JavaObject{
		private int id;
		private String name;
		private String email;
		private String address;

		public int getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getAddress() {
			return address;
		}

		private void setId(int id) {
			this.id = id;
		}

		private void setName(String name) {
			this.name = name;
		}

		private void setEmail(String email) {
			this.email = email;
		}

		private void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return this.name + "#" + this.id + "#" + this.address + "#" + this.email;
		}
	}

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

	/**
	 * <b>function:</b>将java对象转换成json字符串
	 * @author zhangsiwei6
	 * @createDate 2017/1/10 下午15:01
	 */
	@Test
	public void writeEntityJSON() {
		try {
			System.out.println("jsonGenerator");
			//writeObject可以转换java对象，eg:Javabean/Map/List/Array等
			jsonGenerator.writeObject(bean);
			System.out.println();

			System.out.println("ObjectMapper");
			//writeValue具有和writeObject相同的功能
			objectMapper.writeValue(System.out, bean);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>function:</b>将map转换成json字符串
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

			System.out.println("jsonGenerator");
			jsonGenerator.writeObject(map);
			System.out.println();

			System.out.println("objectMapper");
			objectMapper.writeValue(System.out, map);
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>function:</b>将list集合转换成json字符串
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

			System.out.println("jsonGenerator");
			//list转换成JSON字符串
			jsonGenerator.writeObject(list);
			System.out.println();

			System.out.println("ObjectMapper");
			//用objectMapper直接返回list转换成的JSON字符串
			System.out.println("1###" + objectMapper.writeValueAsString(list));
			System.out.print("2###");
			//objectMapper list转换成JSON字符串
			objectMapper.writeValue(System.out, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

