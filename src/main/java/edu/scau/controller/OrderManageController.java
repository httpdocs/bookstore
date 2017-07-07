package edu.scau.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.mapper.DeliveryMapper;
import edu.scau.model.Delivery;
import edu.scau.service.OrderManageService;

@Controller
@RequestMapping("/ordermgr")
public class OrderManageController {

	@Autowired
	private OrderManageService service;
	
	@RequestMapping("/list")
	public void list(HttpServletResponse response){
		String json = service.list();
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/detele")
	public void delete(String orderid,HttpServletResponse response){
		JSONObject json=service.delete(orderid);
		System.out.println(json.toString());
		try {
			response.getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/deliver")
	public void delivery(Delivery delivery, HttpServletResponse response){
		String json = service.deliver( delivery);
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getDelivery")
	public void getDelivery(Delivery delivery,HttpServletResponse response){
		System.err.println("Ctrl orderid"+delivery.getOrderid());
		String json =service.selectByOrderId(delivery);
		try {
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
