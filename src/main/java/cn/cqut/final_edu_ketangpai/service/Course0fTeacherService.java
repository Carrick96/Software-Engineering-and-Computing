package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.CourseExecution;
import cn.cqut.final_edu_ketangpai.entity.CourseOfTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:Course0fTeacherService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-10 14:02
 */
public interface Course0fTeacherService extends IService<CourseOfTeacher> {
	CourseExecution joinCourse(String teacherId, String classId);
}
