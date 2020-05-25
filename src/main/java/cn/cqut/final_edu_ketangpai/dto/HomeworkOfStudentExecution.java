package cn.cqut.final_edu_ketangpai.dto;

import cn.cqut.final_edu_ketangpai.entity.HomeworkOfStudent;
import cn.cqut.final_edu_ketangpai.enums.HomeworkStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @CLASSNAME:CourseOfStudentExecution
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-16 18:04
 */
@Data
public class HomeworkOfStudentExecution {
	//结果状态
	private int state;

	//状态标识
	private String stateInfo;

	//课程数量
	private int count;

	private HomeworkOfStudent homeworkOfStudent;

	private List<HomeworkOfStudent> homeworkOfStudentList;

	public HomeworkOfStudentExecution() {
	}
	// 操作失败的时候使用的构造器
	public HomeworkOfStudentExecution(HomeworkStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功的时候使用的构造器
	public HomeworkOfStudentExecution(HomeworkStateEnum stateEnum, HomeworkOfStudent homeworkOfStudent) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.homeworkOfStudent = homeworkOfStudent;
	}

	// 操作成功的时候使用的构造器
	public HomeworkOfStudentExecution(HomeworkStateEnum stateEnum, List<HomeworkOfStudent> homeworkOfStudentList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.homeworkOfStudentList = homeworkOfStudentList;
	}
}
