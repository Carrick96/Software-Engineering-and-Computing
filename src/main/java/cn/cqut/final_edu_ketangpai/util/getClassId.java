package cn.cqut.final_edu_ketangpai.util;

import java.util.Random;

/**
 * @CLASSNAME:getClassId
 * @description:课程Id即加课码生成
 * @author: Nonameguy
 * @create: 2020-05-06 20:54
 */
public class getClassId {
	public static String generateClassId(int length) {
		String str = "abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(8);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getClassId.generateClassId(8));
	}
}
