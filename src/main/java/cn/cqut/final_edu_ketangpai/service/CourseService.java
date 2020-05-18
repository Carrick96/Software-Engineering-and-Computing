package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.CourseExecution;
import cn.cqut.final_edu_ketangpai.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:CourseService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-06 14:24
 */

public interface CourseService extends IService<Course> {

	/**
	 * 老师创建课程
	 *
	 * @param course
	 * @return
	 */
	CourseExecution addCourse(Course course);

//	Course getCourseById(String courseId);

	/**编辑课程
	 * @param course
	 * @return
	 */
	CourseExecution updateCourse(Course course);

	CourseExecution queryCourseById(String courseId);

	CourseExecution getStudentCourseList(String studentId);

	CourseExecution getTeacherCourseList(String teacherId);

	CourseExecution deleteCourseById(String courseId);

	CourseExecution archiveCourse(int id);

	CourseExecution unarchiveCourse(int id);

	CourseExecution getCourseById(String courseId);

	CourseExecution topCourse(int id);

	CourseExecution untopCourse(int id);
}
