package cn.cqut.final_edu_ketangpai.dao;

import cn.cqut.final_edu_ketangpai.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @CLASSNAME:CourseDao
 * @description:
 * @author: Nonameguy
 * @create: 2020-04-30 01:05
 */
public interface CourseDao extends BaseMapper<Course> {
	/**
	 * 根据courseId查询课程
	 * @param courseId
	 * @return
	 */
	Course selectByCourseId(String courseId);

	/**
	 * 根据studentId查询学生学习了的课程
	 * @param studentId
	 * @return
	 */
	@Select("select c.*" +
			"from " +
			"course c,course_of_student cos,teacher t " +
			"where " +
			"c.course_id = cos.course_id" +
			" and " +
			"c.teacher_id = t.teacher_id" +
			" and " +
			"cos.student_id = #{studentId} " +
			"and" +
			" c.delete_status = 0 ")
	List<Course> getStudentCourses(String studentId);

	/**
	 * 查询老师教授的课程列表
	 * @param teacherId
	 * @return
	 */
	@Select("select c.* from course c,course_of_teacher cot " +
			"where" +
			" c.course_id = cot.course_id " +
			"and" +
			" cot.teacher_id = #{teacherId} " +
			"and " +
			"c.delete_status = 0")
	List<Course> getTeacherCourses(String teacherId);

	/**
	 * 归档课程
	 * @param courseId
	 * @return
	 */
	@Update("update course " +
			"set " +
			"archive_status = 1 " +
			"where " +
			"id = #{id} " +
			"and" +
			" archive_status = 0")
	int archiveCourse(int id);

	/**
	 * 归档课程恢复
	 * @param courseId
	 * @return
	 */
	@Update("update course " +
			"set " +
			"archive_status = 0 " +
			"where " +
			"id = #{id} " +
			"and" +
			" archive_status = 1")
	int unarchiveCourse(int id);

	@Update("update course set top_status = 1 where id= #{id} and top_status = 0")
	int topCourse(int id);

	@Update("update course set top_status = 0 where id= #{id} and top_status = 1")
	int untopCourse(int id);
}
