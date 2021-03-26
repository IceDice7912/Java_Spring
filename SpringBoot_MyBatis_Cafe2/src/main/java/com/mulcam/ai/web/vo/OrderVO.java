package com.mulcam.ai.web.vo;

import java.util.Date;

public class OrderVO {
	
	private String ordermethod,product_name;
	private Long order_group_no,quantity;
	private Date orderdate;
	
	
	
	

	public OrderVO(String ordermethod, String prodname, long quantity, long order_group_no) {
		this(ordermethod,prodname,quantity);
		setOrder_group_no(order_group_no);
	}
	public OrderVO(String ordermethod, String prodname, long quantity) {
		super();
		setOrdermethod(ordermethod);
		setProduct_name(prodname);
		setQuantity(quantity);
		
	}








	public String getOrdermethod() {
		return ordermethod;
	}
	public void setOrdermethod(String ordermethod) {
		this.ordermethod = ordermethod;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Long getOrder_group_no() {
		return order_group_no;
	}
	public void setOrder_group_no(Long order_group_no) {
		this.order_group_no = order_group_no;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	
	@Override
	public String toString() {
		return "OrderVO [ordermethod=" + ordermethod + ", product_name=" + product_name + ", order_group_no="
				+ order_group_no + ", quantity=" + quantity + "]";
	}






}
