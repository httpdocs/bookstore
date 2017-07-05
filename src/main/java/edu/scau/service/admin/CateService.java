package edu.scau.service.admin;

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
	 * 增加分类
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
			json.put("msg", "分类已存在");
		} else {
			try {
				mapper.insertSelective(cate);
				session.commit();
				json.put("status", 0);
				json.put("msg", "分类添加成功");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "操作失败");
			}
		}
		return json.toString();
	}

	/**
	 * 往分类中添加一本书
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
			json.put("msg", "["+cate+"]分类不存在");
		} else if(c.getParent() == null){
			json.put("status", -4);
			json.put("msg", "["+cate+"]分类不是二级分类，请选择一个二级分类");
		} else if (row != null){
			json.put("status", -2);
			json.put("msg", "[" + isbn + "]已存在于[" + cate + "]分类");
		} else {
			try {
				bookMapper.insert(isbn, cate);
				json.put("status", 0);
				json.put("msg", "操作成功");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "操作失败");
			}
		}
		return json.toString();
	}
	
	/**
	 * 从分类中移除一本书
	 * @param isbn
	 * @param cate
	 * @return
	 */
	public String delete(String isbn, String cate){
		JSONObject json = new JSONObject();
		CateHasBookKey row = bookMapper.select(isbn, cate);
		if (row == null) {
			json.put("status", -2);
			json.put("msg", "[" + isbn + "]不存在于[" + cate + "]分类");
		} else {
			try {
				bookMapper.delete(isbn, cate);
				json.put("status", 0);
				json.put("msg", "操作成功");
			} catch (Exception e) {
				json.put("status", -1);
				json.put("msg", "操作失败");
			}
		}
		return json.toString();
	}

	/**
	 * 获取分类树
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

}
