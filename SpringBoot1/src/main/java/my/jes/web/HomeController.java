package my.jes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@ResponseBody
	@RequestMapping("/home")
	public String home() {
		System.out.println("Hello Boot!!(시스템 아웃에서 출력)");
		return "Hello Boot!!(리턴) 빨리 적용 안됨?? 다시 한 번 해본다";
	}
}
