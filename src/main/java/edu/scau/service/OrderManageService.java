package edu.scau.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.scau.mapper.DeliveryMapper;
import edu.scau.mapper.OrderMapper;
import edu.scau.model.Delivery;
import edu.scau.model.Order;
import edu.scau.util.JSONUtil;

@Service
public class OrderManageService {

	@Autowired
	private OrderMapper mapper;
	
	@Autowired
	private DeliveryMapper deliveryMapper;
	
	/**
	 * 所有订单
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
	
	public String deliver(int orderId, Delivery delivery){
		JSONObject json = new JSONObject();
		try{
			Order order = mapper.selectByPrimaryKey(orderId);
			if(order == null){
				json.put("status", -2);
				json.put("msg", "订单不存在");
			}
			deliveryMapper.insert(delivery);
			mapper.delivery(orderId);
		} catch (Exception e) {
			json.put("status", -1);
			json.put("msg", "操作失败");
		}
		json.put("status", 0);
		json.put("msg", "发货成功");
		return json.toString();
	}
}
