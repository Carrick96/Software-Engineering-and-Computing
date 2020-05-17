package cn.cqut.final_edu_ketangpai.enums;

import lombok.Getter;

@Getter
public enum CourseOfStudentEnum {
	SUCCESS(1, "操作成功"),
	INNER_ERROR(-1001,"内部系统错误"),
	NULL_CourseOfStudentID(-1002, "CourseOfStudentId为空"),
	NULL_CourseOfStudent(-1003, "CourseOfStudent信息为空");
	private Integer state;
	private String stateInfo;

	CourseOfStudentEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/**
	 * 依据传入的state返回相应的enum值
	 */
	public static CourseOfStudentEnum stateOf(int state) {
		for (CourseOfStudentEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
}
