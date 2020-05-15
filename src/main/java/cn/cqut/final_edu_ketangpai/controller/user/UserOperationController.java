package cn.cqut.final_edu_ketangpai.controller.user;

import cn.cqut.final_edu_ketangpai.dto.UserExecution;
import cn.cqut.final_edu_ketangpai.entity.RoleOfUser;
import cn.cqut.final_edu_ketangpai.entity.Teacher;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.enums.RoleEnum;
import cn.cqut.final_edu_ketangpai.enums.StatusEnum;
import cn.cqut.final_edu_ketangpai.enums.UserStateEnum;
import cn.cqut.final_edu_ketangpai.service.RoleOfUserService;
import cn.cqut.final_edu_ketangpai.service.TeacherService;
import cn.cqut.final_edu_ketangpai.service.UserService;
import cn.cqut.final_edu_ketangpai.util.CodeUtil;
import cn.cqut.final_edu_ketangpai.util.HttpServletRequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @CLASSNAME:UserController
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 18:42
 */
@RestController
@RequestMapping("/index/user")
public class UserOperationController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleOfUserService roleOfUserService;
	@Autowired
	private TeacherService teacherService;

	@PostMapping("/auth")
	private Map<String, Object> login(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 获取输入的帐号
		String username = HttpServletRequestUtil.getString(request, "username");
		// 获取输入的密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 非空校验
		if (username != null && password != null) {
			// 传入帐号和密码去获取平台帐号信息
			UserExecution userExecution = userService.selectUser(username, password);
			RoleOfUser userRole = roleOfUserService.getUserRole(userExecution.getUser().getUserId(), userExecution.getUser().getRoleId());

			if (userRole != null) {
				// 若能取到权限信息则登录成功
				modelMap.put("success", true);
				modelMap.put("user_role",userExecution.getUser().getRoleId());
				// 同时在session里设置用户信息
				request.getSession().setAttribute("SESSION_USER_INFO", userExecution.getUser());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
		}
		return modelMap;
	}

	@PostMapping("/registeruser")
	private Map<String, Object> registerUser(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		if (CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", StatusEnum.VERIFY_CODE_ERROR.getStateInfo());
			return modelMap;
		}
		String userStr = HttpServletRequestUtil.getString(request, "userStr");
		ObjectMapper mapper = new ObjectMapper();
		User user;
		try {
			assert userStr != null;
			user = mapper.readValue(userStr, User.class);
			user.setCreateTime(LocalDateTime.now());
		} catch (JsonProcessingException e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", StatusEnum.REGISTER_FAILED + "," + "原因是： " + e.getMessage());
			return modelMap;
		}
		// 2.注册
		UserExecution userExecution = userService.register(user);
		if (userExecution.getUser() == null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "注册失败，原因是： " + UserStateEnum.NULL_USER.getStateInfo());
		}
		if (userExecution.getUser().getRoleId().equals(RoleEnum.TEACHER_ROLE.getCode())) {
			Teacher teacher = new Teacher();
			teacher.setTeacherId(userExecution.getUser().getUserId());
			teacher.setTeacherName(userExecution.getUser().getUsername());
			int result = teacherService.addTeacher(teacher);
			if (result <= 0) {
				modelMap.put("success", true);
				modelMap.put("errMsg","注册失败，原因是： "+UserStateEnum.NULL_USER.getStateInfo());
				return modelMap;
			}
		}
		RoleOfUser roleOfUser = new RoleOfUser();
		roleOfUser.setUserId(userExecution.getUser().getUserId());
		roleOfUser.setRoleId(userExecution.getUser().getRoleId());
		int result = roleOfUserService.addRole(roleOfUser);
		if (result < 0) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "注册失败，原因是： " + StatusEnum.SERVICE_ERROR.getStateInfo());
		}
		modelMap.put("success", true);
		return modelMap;
	}

}
