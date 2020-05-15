package cn.cqut.final_edu_ketangpai.enums;

import lombok.Getter;

/**
 * @CLASSNAME:HomeworkStateEnum
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 22:48
 */
@Getter
public enum  HomeworkStateEnum {
	SUCCESS(1, "操作成功"),
	INNER_ERROR(-1001,"内部系统错误"),
	NULL_HOMEWORKID(-1002, "HomeworkId为空"),
	NULL_HOMEWORK(-1003, "homework信息为空");
	private Integer state;
	private String stateInfo;

	HomeworkStateEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/**
	 * 依据传入的state返回相应的enum值
	 */
	public static HomeworkStateEnum stateOf(int state) {
		for (HomeworkStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
}
