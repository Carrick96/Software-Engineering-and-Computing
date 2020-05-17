package cn.cqut.final_edu_ketangpai.dto;

import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.enums.UserStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @CLASSNAME:CourseExecution
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-09 22:33
 */
@Data
public class UserExecution {
	//结果状态
	private int state;

	//状态标识
	private String stateInfo;

	//课程数量
	private int count;

	//操作的(增删改课程的时候使用)
	private User user;

	//列表（查询时使用）
	private List<User> userList;

	public UserExecution(){

	}
	// 操作失败的时候使用的构造器
	public UserExecution(UserStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功的时候使用的构造器
	public UserExecution(UserStateEnum stateEnum, User user) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}

	// 操作成功的时候使用的构造器
	public UserExecution(UserStateEnum stateEnum, List<User> userList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.userList = userList;
	}
}
