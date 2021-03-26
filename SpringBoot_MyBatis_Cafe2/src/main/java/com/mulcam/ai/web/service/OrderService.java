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
//			Socket s = ss.accept();
//			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
//			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			new Thread(()-> {
					while(true) {
						try {
						Socket s = ss.accept();
						ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
						ObjectInputStream in = new ObjectInputStream(s.getInputStream());	
						
						new KitchenThread(s, in, out).start();
						
						} catch(IOException e) {
							System.out.println("Error : " + e);
						}
					}
				}
			).start();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public long insert(ArrayList<OrderVO> list) {
		long order_group_no = orderDAO.insert(list);
		out.writeObject();
		return order_group_no;
	}	
	
	private class KitchenThread extends Thread {
		private Socket s;
		private ObjectInputStream in;
		private ObjectOutputStream out;

		public KitchenThread(Socket s, ObjectInputStream in, ObjectOutputStream out) {
			this.s=s;
			this.in=in;
			this.out=out;
		}

		@Override
		public void run() {
			try {
				while(true) {
					in.readObject();
				} } catch (Exception e) {
					System.out.println("에러 : " + e);
				}
			}
		}
	}
