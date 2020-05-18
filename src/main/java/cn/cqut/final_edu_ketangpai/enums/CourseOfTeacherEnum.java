package cn.cqut.final_edu_ketangpai.enums;

import lombok.Getter;

@Getter
public enum CourseOfTeacherEnum {
	SUCCESS(1, "操作成功"),
	INNER_ERROR(-1001,"内部系统错误"),
	NULL_COUESEOFTEACHERID(-1002, "CourseOfTeacherId为空"),
	NULL_COURSEOFTEACHER(-1003, "CourseOfTeacher信息为空");
	private Integer state;
	private String stateInfo;

	CourseOfTeacherEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/**
	 * 依据传入的state返回相应的enum值
	 */
	public static CourseOfTeacherEnum stateOf(int state) {
		for (CourseOfTeacherEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}
}
