package edu.scau.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scau.mapper.OrderMapper;
import edu.scau.model.Order;
import edu.scau.util.JSONUtil;

@Service
public class OrderManageService {

	@Autowired
	private OrderMapper mapper;
	
	/**
	 * 鎵�鏈夎鍗�
	 * @return
	 */
	public String list(){
		List<Order> list =  mapper.selectAll();
		JSONArray array = JSONUtil.listToArray(list, Order.class);
		return array.toString();
	}
	
	
	public JSONObject delete(String orderid){
		JSONObject json = new JSONObject();
		try {
			mapper.deleteByPrimaryKey(orderid);
			json.put("status", 0);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", -1);
			json.put("msg", "删除失败");
		}
		return json;
	}
	
}
