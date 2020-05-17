package cn.cqut.final_edu_ketangpai.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @CLASSNAME:IndexController
 * @description:解析路由并且进行转发
 * @author: Nonameguy
 * @create: 2020-05-05 13:45
 */
@Controller
public class IndexController {
	@GetMapping("/index")
	private String home(){
		return "/index";
	}
	@GetMapping("/register")
	private String register() {
		return "/register";
	}
	@GetMapping("login")
	private String auth(){
		return "/login";
	}
}
