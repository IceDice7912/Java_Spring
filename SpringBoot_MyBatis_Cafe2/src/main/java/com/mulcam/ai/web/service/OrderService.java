package com.mulcam.ai.web.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
			ss=new ServerSocket(9999);
			Socket s = ss.accept();
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public long insert(ArrayList<OrderVO> list) {
		return orderDAO.ordersInsert(list);
	}
	
}
