package cn.cqut.final_edu_ketangpai.enums;

import lombok.Getter;

/**
 * @author Nonameguy
 */
@Getter
public enum StatusEnum {
	/**
	 * 响应状态枚举
	 * */
	SERVICE_SUCCESS(200,"成功"),
	SUCCESS(1, "操作成功"),
	FAILED(-1002, "失败"),
	PARAM_ERROR(40001,"入参异常"),
	ERROR_20011(20011, "登陆已过期,请重新登陆"),
	SERVICE_ERROR(500,"服务异常"),
	LOGIN_ERROR(414,"登录失败"),
	REGISTER_FAILED(415,"注册失败"),
	OPERATE_FAIL(444,"操作失败"),
	VERIFY_CODE_ERROR(416,"验证码错误");
	private Integer state;
	private String stateInfo;


	StatusEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

}
