package com.mulcam.ai.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mulcam.ai.web.service.MemberService;
import com.mulcam.ai.web.vo.MemberVO;

@Controller 

public class MemberController { 

 

 

@Autowired MemberService memberService; 

  
@RequestMapping(value = "login.jes",  

method= {RequestMethod.POST}, 

produces = "application/text; charset=utf8")			 

@ResponseBody 

public String login(HttpServletRequest request, 

HttpServletResponse response){ 

String id=request.getParameter("id"); 

String pw=request.getParameter("pw");		 

 

try { 

MemberVO m=new MemberVO(id,pw);  

String name=memberService.login(m); 

if(name!=null) { 

HttpSession session=request.getSession(); 

session.setAttribute("member", m); 

return id+"님 접속중"; 

}else { 

return "로그인 실패"; 

} 

}catch(Exception e) { 

return e.getMessage(); 

}		 

} 


@RequestMapping(value = "memberInsert.jes",  

method= {RequestMethod.POST}, 

produces = "application/text; charset=utf8")			 

@ResponseBody 

public String memberInsert(HttpServletRequest request, 

HttpServletResponse response){ 

String id=request.getParameter("id"); 

String pw=request.getParameter("pw"); 

String name=request.getParameter("name"); 

System.out.println("memberInsert:"+id+"\t"+pw+"\t"+name); 

 

try { 

MemberVO m=new MemberVO(id,pw,name);  

memberService.memberInsert(m); 

return name+"님 회원가입 되셨습니다"; 

}catch(Exception e) { 

return e.getMessage(); 

}		 

} 

} 		 
