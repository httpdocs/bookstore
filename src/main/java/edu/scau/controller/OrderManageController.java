//package edu.scau.controller;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import edu.scau.service.OrderManageService;
//
//@Controller
//@RequestMapping("/ordermgr")
//public class OrderManageController {
//
//	@Autowired
//	private OrderManageService service;
//
//	public void list(HttpServletResponse response){
//		String json = service.list();
//		try {
//			response.getWriter().println(json);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//}
