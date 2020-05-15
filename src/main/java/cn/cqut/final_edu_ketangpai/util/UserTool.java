package cn.cqut.final_edu_ketangpai.util;


import cn.cqut.final_edu_ketangpai.entity.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @CLASSNAME:UserTool
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-10 01:16
 */
public class UserTool {
	static RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
	static HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

	//获取当前用户的用户信息
	public static User getCurrentUser(){
		return (User) request.getSession().getAttribute("SESSION_USER_INFO");
	}
}
