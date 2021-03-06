package edu.scau.mapper.view;

import java.math.BigDecimal;
import java.util.List;

import edu.scau.model.OrderHasBook;

public class OrderDetail {

	private String userId;
	private Integer orderId;
	private BigDecimal total;
	private String name;
	private String tel;
	private String address;
	
	private List<OrderHasBook> list;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setList(List<OrderHasBook> list) {
		this.list = list;
	}
	public List<OrderHasBook> getList() {
		return list;
	}

}
