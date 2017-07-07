package edu.scau.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scau.mapper.CateHasBookMapper;
import edu.scau.mapper.CateMapper;
import edu.scau.model.Cate;
import edu.scau.model.CateHasBookKey;
import edu.scau.util.DBSessionFactory;

@Service
public class CateService {

	@Autowired
	private CateMapper mapper;

	private CateHasBookMapper bookMapper;

	/**
	 * 澧炲姞鍒嗙被
	 * 
	 * @param cate
	 * @return
	 */
	public String create(Cate cate) {
		SqlSession session = DBSessionFactory.openSession();
		CateMapper mapper = session.getMapper(CateMapper.class);
		Cate c = mapper.selectByPrimaryKey(cate.getCate());
		JSONObject json = new JSONObject();
		if (c != null) {
			json.put("status", -2);
			json.put("msg", "鍒嗙被宸插瓨鍦�");
		} else {
			try {
				mapper.insertSelective(cate);
				session.commit();
				json.put("status", 0);
				json.put("msg", "鍒嗙被娣诲姞鎴愬姛");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "鎿嶄綔澶辫触");
			}
		}
		return json.toString();
	}

	/**
	 * 寰�鍒嗙被涓坊鍔犱竴鏈功
	 * 
	 * @param isbn
	 * @param cate
	 * @return
	 */
	public String add(String isbn, String cate) {
		JSONObject json = new JSONObject();
		CateHasBookKey row = bookMapper.select(isbn, cate);
		Cate c = mapper.selectByPrimaryKey(cate);
		if (c == null) {
			json.put("status", -3);
			json.put("msg", "["+cate+"]鍒嗙被涓嶅瓨鍦�");
		} else if(c.getParent() == null){
			json.put("status", -4);
			json.put("msg", "["+cate+"]鍒嗙被涓嶆槸浜岀骇鍒嗙被锛岃閫夋嫨涓�涓簩绾у垎绫�");
		} else if (row != null){
			json.put("status", -2);
			json.put("msg", "[" + isbn + "]宸插瓨鍦ㄤ簬[" + cate + "]鍒嗙被");
		} else {
			try {
				bookMapper.insert(isbn, cate);
				json.put("status", 0);
				json.put("msg", "鎿嶄綔鎴愬姛");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "鎿嶄綔澶辫触");
			}
		}
		return json.toString();
	}
	
	/**
	 * 浠庡垎绫讳腑绉婚櫎涓�鏈功
	 * @param isbn
	 * @param cate
	 * @return
	 */
	public String delete(String isbn, String cate){
		JSONObject json = new JSONObject();
		CateHasBookKey row = bookMapper.select(isbn, cate);
		if (row == null) {
			json.put("status", -2);
			json.put("msg", "[" + isbn + "]涓嶅瓨鍦ㄤ簬[" + cate + "]鍒嗙被");
		} else {
			try {
				bookMapper.delete(isbn, cate);
				json.put("status", 0);
				json.put("msg", "鎿嶄綔鎴愬姛");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "鎿嶄綔澶辫触");
			}
		}
		return json.toString();
	}

	/**
	 * 鑾峰彇鍒嗙被鏍�
	 * 
	 * @return
	 */
	public String list() {
		List<Cate> list = mapper.list();
		if (list == null) {
			return null;
		}
		Map<String, List<String>> map = new HashMap<>();
		for (Cate cate : list) {
			if (cate.getParent() == null) {
				if (!map.containsKey(cate.getCate())) {
					map.put(cate.getCate(), new ArrayList<>());
				}
			} else {
				if (!map.containsKey(cate.getParent())) {
					map.put(cate.getParent(), new ArrayList<>());
				} else {
					map.get(cate.getParent()).add(cate.getCate());
				}
			}
		}
		JSONArray array = new JSONArray();
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			JSONObject parent = new JSONObject();
			JSONArray children = new JSONArray();
			String key = iter.next();
			List<String> clist = map.get(key);
			parent.put("parent", key);
			for (String child : clist) {
				children.put(child);
			}
			parent.put("children", children);
			array.put(parent);
		}
		return array.toString();
	}

	public String destroy(String cate){
		JSONObject json = new JSONObject();
		try{
			mapper.deleteByPrimaryKey(cate);
			json.put("status", 0);
			json.put("msg", "鎿嶄綔鎴愬姛");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", -1);
			json.put("msg", "鎿嶄綔澶辫触");
		}
		return json.toString();
	}
	
}
