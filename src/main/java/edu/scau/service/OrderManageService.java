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
	
	/**
	 * 发货
	 * @param orderId
	 * @param delivery
	 * @return
	 */
	public String deliver(Delivery delivery){
		JSONObject json = new JSONObject();
		try{
			Order order = mapper.selectByPrimaryKey(delivery.getOrderid());
			if(order == null){
				json.put("status", -2);
				json.put("msg", "订单不存在");
				return json.toString();
			}
//			System.err.println(delivery.getOrderid()+"deID:"+delivery.getDeliveryid()+"com"+delivery.getCompany()+"time"+delivery.getTime());
			deliveryMapper.insert(delivery);
			mapper.delivery(delivery.getOrderid());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", -1);
			json.put("msg", "操作失败");
			return json.toString();
		}
		json.put("status", 0);
		json.put("msg", "发货成功");
		return json.toString();
	}


	public String selectByOrderId(Delivery delivery) {	
//		System.err.println(delivery.getDeliveryid()+delivery.getOrderid());
		Delivery delivery2Return = deliveryMapper.selectByOrderId(delivery.getOrderid());
		System.err.println("Service  devid "+delivery2Return.getDeliveryid()+" com "+delivery2Return.getCompany()+" orderid "+delivery2Return.getOrderid());
		JSONObject json = new JSONObject(delivery2Return);
		return json.toString();
	}


	
}
