package com.imooc.sell.util;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.sell.form.ItemForm;

/**
 * 
 * @author 严凯新
 * @createTime 2017年11月16日 下午11:33:32
 * @version 1.0
 */
public class JsonUtil {
	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 将对象转换成json字符串。
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json转对象
	 * @param jsonData
	 * @param beanType
	 * @param <T>
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * <p>
	 * Title: jsonToList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
		/*if(!jsonData.startsWith("[") && !jsonData.endsWith("]")){
			StringBuffer buffer = new StringBuffer();
			buffer.append("[").append(jsonData).append("]");
			jsonData = buffer.toString();
		}*/
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		String str = "[{\"productId\": \"1423113435324\",\"productQuantity\": 2}]";
		List<ItemForm> itemForms = jsonToList(str, ItemForm.class);
		System.out.println(itemForms.size());
	}
}
