package cn.cqut.final_edu_ketangpai.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @CLASSNAME:CodeUtil
 * @description: 用于验证码的验证
 * @author: Nonameguy
 * @create: 2020-05-05 18:51
 */
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		return verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected);
	}
	public static String generateId() {
		char[] c = CharacterUtil.AllCharacter.charArray();//获取包含26个字母大小写和数字的字符数组
//        System.out.println(Arrays.toString(c));

		Random rd = new Random();
		StringBuilder code = new StringBuilder();
		for (int k = 0; k <= 5; k++) {
			int index = rd.nextInt(c.length);//随机获取数组长度作为索引
			code.append(c[index]);//循环添加到字符串后面
		}
		return code.toString();


	}
}
