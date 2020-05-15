package cn.cqut.final_edu_ketangpai.enums;

import lombok.Getter;

@Getter
public enum CourseStateEnum {
	 SUCCESS(1, "操作成功"),
	INNER_ERROR(-1001,"内部系统错误"),
	NULL_COURSEID(-1002, "CourseId为空"),
	NULL_COURSE(-1003, "course信息为空");
	private Integer state;
	private String stateInfo;

	CourseStateEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/**
	 * 依据传入的state返回相应的enum值
	 */
	public static CourseStateEnum stateOf(int state) {
		for (CourseStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}

}
