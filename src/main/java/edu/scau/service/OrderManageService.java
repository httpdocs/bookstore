package edu.scau.service;

import java.util.List;

import org.json.JSONArray;
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
	 * 所有订单
	 * @return
	 */
	public String list(){
		List<Order> list =  mapper.selectAll();
		JSONArray array = JSONUtil.listToArray(list, Order.class);
		return array.toString();
	}
	
}
