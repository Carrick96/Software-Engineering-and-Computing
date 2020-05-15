package cn.cqut.final_edu_ketangpai.dto;

import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.enums.CourseStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @CLASSNAME:HomeworkExecution
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 00:01
 */
@Data
public class HomeworkExecution {
	private int state;
	private String stateInfo;
	private Homework homework;
	//course列表（查询时使用）
	private List<Homework> homeworkList;

	public HomeworkExecution(){

	}
	// 店铺操作失败的时候使用的构造器
	public HomeworkExecution(CourseStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 店铺操作成功的时候使用的构造器
	public HomeworkExecution(CourseStateEnum stateEnum, Homework homework) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.homework = homework;
	}

	// 店铺操作成功的时候使用的构造器
	public HomeworkExecution(CourseStateEnum stateEnum, List<Homework> homeworkList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.homeworkList = homeworkList;
	}
}
