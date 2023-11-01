package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/*	슬래쉬로 매핑 값을 지정하면 첫 도메인을 의미하며 home.html이 호출된다.
	 * 	resources/static에 index.html이 있지만 home.html이 호출 되는 이유
	 * 	- 요청이 오면 컨테이너에서 관련 컨트롤러가 있는지 먼저 찾고 없다면 static파일을 찾도록 되어있기 때문이다.
	 * 	- 그래서 static폴더에 index.html이 있어도 컨트롤러에서 먼저 찾기 때문에 home.html이 호출되는 이유이다. 
	 */
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
}
