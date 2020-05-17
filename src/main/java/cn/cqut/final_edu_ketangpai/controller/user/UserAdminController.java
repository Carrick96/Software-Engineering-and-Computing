package cn.cqut.final_edu_ketangpai.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @CLASSNAME:UserAdminController
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 19:44
 */
@Controller
public class UserAdminController {

	@GetMapping("/studentregister")
	private String studentRegister() {
		return "/StudentRegister";
	}

	@GetMapping("/teacherregister")
	private String teacherRegister(){
		return "/TeacherRegister";
	}
}
