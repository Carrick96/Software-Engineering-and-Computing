package cn.cqut.final_edu_ketangpai.dto;

import lombok.Data;

import java.io.InputStream;

/**
 * @CLASSNAME:ImageHolder
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-25 08:00
 */
@Data
public class ImageHolder {
	private String imageName;
	private InputStream image;

	public ImageHolder(String imageName, InputStream image) {
		this.imageName = imageName;
		this.image = image;
	}
}
