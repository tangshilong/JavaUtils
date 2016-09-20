package com.xiechanglei.code.utils;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class JsonUtil {

	public static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object o) throws Exception {
		return objectMapper.writeValueAsString(o);
	}

	public static <T> T fromJson(String str, Class<T> t) throws Exception {
		return objectMapper.readValue(str, t);
	}

	public static <T> Object fromJson(String str, TypeReference<List<T>> typeReference) throws Exception {
		return objectMapper.readValue(str, typeReference);
	}
}
