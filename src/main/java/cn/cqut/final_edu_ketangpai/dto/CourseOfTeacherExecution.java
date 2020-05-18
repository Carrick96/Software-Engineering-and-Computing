package cn.cqut.final_edu_ketangpai.dto;

import cn.cqut.final_edu_ketangpai.entity.CourseOfTeacher;
import cn.cqut.final_edu_ketangpai.enums.CourseOfTeacherEnum;
import lombok.Data;

import java.util.List;

/**
 * @CLASSNAME:CourseOfStudentExecution
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-16 18:04
 */
@Data
public class CourseOfTeacherExecution {
	//结果状态
	private int state;

	//状态标识
	private String stateInfo;

	//课程数量
	private int count;

	private CourseOfTeacher courseOfTeacher;

	private List<CourseOfTeacher> courseOfTeacherList;

	public CourseOfTeacherExecution() {
	}
	// 操作失败的时候使用的构造器
	public CourseOfTeacherExecution(CourseOfTeacherEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 操作成功的时候使用的构造器
	public CourseOfTeacherExecution(CourseOfTeacherEnum stateEnum, CourseOfTeacher courseOfTeacher) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.courseOfTeacher = courseOfTeacher;
	}

	// 操作成功的时候使用的构造器
	public CourseOfTeacherExecution(CourseOfTeacherEnum stateEnum, List<CourseOfTeacher> courseOfTeacherList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.courseOfTeacherList = courseOfTeacherList;
	}
}
