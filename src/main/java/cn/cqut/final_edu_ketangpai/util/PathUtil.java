package cn.cqut.final_edu_ketangpai.util;

/**
 * @CLASSNAME:PathUtil
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-25 03:59
 */
public class PathUtil {
	private static String seperator = System.getProperty("file.separator");

	/**
	 * 返回文件的根路径
	 *
	 * @return
	 */
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image";
		} else {
			basePath = "/Users/baidu/work/image";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	/**
	 * 根据不同需求返回文件的子路径
	 *
	 * @param homeworkId
	 * @return
	 */
	public static String getShopImagePath(String homeworkId, String studentId) {
		String imagePath = "/upload/images/item/shop/" + homeworkId + studentId + "/";
		return imagePath.replace("/", seperator);
	}
}
