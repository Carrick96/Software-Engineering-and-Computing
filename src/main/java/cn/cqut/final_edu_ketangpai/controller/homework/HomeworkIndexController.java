package cn.cqut.final_edu_ketangpai.controller.homework;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @CLASSNAME:HomeworkIndexController
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-19 19:36
 */
@Controller
public class HomeworkIndexController {
	@GetMapping("/studenthomework")
	private String studentHomework() { return "/studenthomework"; }
	@GetMapping("/teacherhomework")
	private String teacherHomework() { return "/teacherhomework"; }

}
