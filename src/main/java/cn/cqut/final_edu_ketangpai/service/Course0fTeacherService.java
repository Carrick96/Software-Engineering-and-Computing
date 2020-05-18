package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.CourseExecution;
import cn.cqut.final_edu_ketangpai.dto.CourseOfTeacherExecution;
import cn.cqut.final_edu_ketangpai.entity.CourseOfTeacher;
import cn.cqut.final_edu_ketangpai.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @CLASSNAME:Course0fTeacherService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-10 14:02
 */
public interface Course0fTeacherService extends IService<CourseOfTeacher> {
	CourseExecution joinCourse(String teacherId, String classId);

	List<User> getTeacherName(String courseId);

	CourseOfTeacherExecution getTeacherNum(String courseId);
}
