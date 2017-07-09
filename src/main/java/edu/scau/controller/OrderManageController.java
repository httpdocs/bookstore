package edu.scau.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.scau.mapper.DeliveryMapper;
import edu.scau.model.Delivery;
import edu.scau.model.Order;
import edu.scau.model.OrderAndDelivery;
import edu.scau.service.OrderManageService;

@Controller
@RequestMapping("/ordermgr")
public class OrderManageController {

	@Autowired
	private OrderManageService service;
	
	private OrderAndDelivery orderAndDelivery;
	
	@RequestMapping("/getOad")
	public void oad(HttpServletResponse response){
		String json = service.getOad();
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
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
		try {
		System.err.println( delivery + "  " + delivery.getOrderid());
		String json = service.deliver(delivery);
		JSONObject jsonObject = new JSONObject(json);
	
			response.getWriter().println(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getDelivery")
	public void getDelivery(Order order,HttpServletResponse response){
		System.err.println("Ctrl orderid"+order.getOrderid());
		String json =service.selectByOrderId(order);
		try {
			response.getWriter().println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
