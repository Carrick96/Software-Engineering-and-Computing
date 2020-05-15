package cn.cqut.final_edu_ketangpai.util;

/**
 * @CLASSNAME:CharacterUtil
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 00:00
 */
public class CharacterUtil {
	/**
	 * 包含了二十六个字母和十个数字的字符数组
	 */
	public static class AllCharacter {
		public static char[] charArray() {
			int i = 1234567890;
			String s = "qwertyuiopasdfghjklzxcvbnm";
			String s2 = s.toUpperCase();
			String word = s + s2 + i;
			char[] c = word.toCharArray();

			return c;
		}
	}
}
