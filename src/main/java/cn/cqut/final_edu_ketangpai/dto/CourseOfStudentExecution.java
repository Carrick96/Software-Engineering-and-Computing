package cn.cqut.final_edu_ketangpai.dto;

import cn.cqut.final_edu_ketangpai.entity.CourseOfStudent;
import cn.cqut.final_edu_ketangpai.enums.CourseOfStudentEnum;
import lombok.Data;

import java.util.List;

/**
 * @CLASSNAME:CourseOfStudentExecution
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-16 18:04
 */
@Data
public class CourseOfStudentExecution {
	//结果状态
	private int state;

	//状态标识
	private String stateInfo;

	//课程数量
	private int count;

	private CourseOfStudent courseOfStudent;

	private List<CourseOfStudent> courseOfStudentList;

	public CourseOfStudentExecution() {
	}
	// 操作失败的时候使用的构造器
	public CourseOfStudentExecution(CourseOfStudentEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功的时候使用的构造器
	public CourseOfStudentExecution(CourseOfStudentEnum stateEnum, CourseOfStudent courseOfStudent) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.courseOfStudent = courseOfStudent;
	}

	// 操作成功的时候使用的构造器
	public CourseOfStudentExecution(CourseOfStudentEnum stateEnum, List<CourseOfStudent> courseOfStudentList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.courseOfStudentList = courseOfStudentList;
	}
}
