package com.hikvision.mdp.web.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

@SuppressWarnings("all")
public class ConvertJsonPojo {

	private static Logger log = LoggerFactory.getLogger(ConvertJsonPojo.class);
	
	private static ObjectMapper MAPPER = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);;

	/**
	 * 将pojo对象转化为json
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public static <T> String pojoToJson(T object) {
		String result = null;
		if(object instanceof String){
			return (String)object;
		}
		try {
			result = MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("程序出错", e);
		}
		return result;
	}

	/**
	 * 将输入的JSONObject格式对象转化为Pojo对象
	 * 
	 * @param clazz
	 * @param jsonObject
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> T jsonToPojo(Class<T> clazz, JsonNode jsonNode) {
		T result = null;
		try {
			result = MAPPER.readValue(jsonNode.toString(), clazz);
		} catch (Exception e) {
			log.error("程序出错", e);
		}
		return result;

	}

	/**
	 * 将输入的String格式对象转化为Pojo对象
	 * 
	 * @param clazz
	 * @param string
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> T jsonToPojo(Class<T> clazz, String string) {

		T result = null;
		try {
			result = MAPPER.readValue(string, clazz);
		} catch (Exception e) {
			log.error("程序出错", e);
		}
		return result;
	}

	/**
	 * 将输入的inputStream格式对象转化为Pojo对象
	 * 
	 * @param clazz
	 * @param string
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> T jsonToPojo(Class<T> clazz, InputStream inputStream) {
		T result = null;
		try {
			result = MAPPER.readValue(inputStream, clazz);
		} catch (Exception e) {
			log.error("程序出错", e);
		}
		return result;
	}

	/**
	 * 
	 * @param ref
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToPojo(TypeReference<T> ref, InputStream inputStream) {
		T result = null;
		try {
			result = MAPPER.readValue(inputStream, ref);
		} catch (Exception e) {
			log.error("程序出错", e);
		}
		return result;
	}

	public static <T> T jsonToPojo(TypeReference<T> ref, String string) {
		T result = null;
		try {
			result = MAPPER.readValue(string, ref);
		} catch (Exception e) {
			log.error("程序出错", e);
		}
		return result;
	}

	public static <T> T jsonToPojo(CollectionType type, String string) {
		T result = null;
		try {
			result = MAPPER.readValue(string, type);
		} catch (Exception e) {
			log.error("程序出错", e);
		}
		return result;
	}

	public static <T> T jsonToPojo(CollectionType type, InputStream inputStream) {
		T result = null;
		try {
			result = MAPPER.readValue(inputStream, type);
		} catch (Exception e) {
			log.error("程序出错", e);
		}
		return result;
	}

}
