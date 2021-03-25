package com.mulcam.ai.web.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mulcam.ai.web.dao.OrderDAOImpl;
import com.mulcam.ai.web.vo.OrderVO;

@Service
public class OrderService {

	@Autowired
	OrderDAOImpl orderDAO;
	ServerSocket ss;
	
	public OrderService() {
		try {
			ss=new ServerSocket();
			ss.accept();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public long insert(ArrayList<OrderVO> list) {
		return orderDAO.insert(list);
	}
	
}
