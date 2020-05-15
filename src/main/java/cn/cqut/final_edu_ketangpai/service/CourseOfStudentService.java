package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.CourseExecution;
import cn.cqut.final_edu_ketangpai.entity.CourseOfStudent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:CourseOfStudentService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-09 16:15
 */
public interface CourseOfStudentService extends IService<CourseOfStudent> {
	//学生根据加课码加入课程
	CourseExecution studentJoinCourse(String studentId,String courseId);

	CourseExecution selectCountOfStudent(String courseId);
}
