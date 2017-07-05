package edu.scau.util;

import java.lang.reflect.Field;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtil {

	public static <T> JSONArray listToArray(List<T> list, Class<T> cls){
		JSONArray array = new JSONArray();
		Field[] fields = cls.getDeclaredFields();
		for(T  t : list){
			JSONObject object = new JSONObject();
			for(Field f : fields){
				try {
					f.setAccessible(true);
					object.put(f.getName(), f.get(t));
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			array.put(object);
		}
		return array;
	}
	
	public static <T> JSONObject objectToObject(Object obj, Class<T> cls){
		Field[] fields = cls.getDeclaredFields();
		JSONObject json = new JSONObject();
		for(Field f : fields){
			f.setAccessible(true);
			try {
				json.put(f.getName(), f.get(obj));
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return json;
	}
	
}
