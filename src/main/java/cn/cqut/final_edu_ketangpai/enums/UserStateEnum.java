package cn.cqut.final_edu_ketangpai.enums;

import lombok.Getter;

@Getter
public enum UserStateEnum {
	 SUCCESS(1, "操作成功"),
	INNER_ERROR(-1001,"内部系统错误"),
	NULL_USER(-1003, "user信息为空");
	private Integer state;
	private String stateInfo;

	UserStateEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/**
	 * 依据传入的state返回相应的enum值
	 */
	public static UserStateEnum stateOf(int state) {
		for (UserStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}

}
