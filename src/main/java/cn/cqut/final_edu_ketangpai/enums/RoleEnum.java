package cn.cqut.final_edu_ketangpai.enums;

import lombok.Getter;

/**
 * 权限枚举
 */
@Getter
public enum RoleEnum {
STUDENT_ROLE(2,"学生"),
	TEACHER_ROLE(1,"教师");
	private Integer code;
	private String message;
	RoleEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
