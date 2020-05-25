package cn.cqut.final_edu_ketangpai.dao;

import cn.cqut.final_edu_ketangpai.entity.CourseOfStudent;
import cn.cqut.final_edu_ketangpai.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @CLASSNAME:CourseOfStudentDao
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-04 01:45
 */
public interface CourseOfStudentDao extends BaseMapper<CourseOfStudent> {

	/**
	 * 学生加入课程
	 *
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	@Insert("insert into course_of_student (course_id,student_id) values ((select course_id from course where course_id = #{courseId}),#{studentId}) ")
	int joinCourse(String courseId, String studentId);

	/**
	 * 查询选修了课程的学生数量
	 *
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	/*@Select("select count(cos.student_id) " +
			"from course_of_student cos,course c " +
			"where c.course_id = cos.course_id")*/
	int getStudentNum(@Param("courseId") String courseId);


	/**
	 * 通过courseId查询选修了课程的学生姓名
	 *
	 * @param courseId
	 * @return
	 */
	List<User> getStuName(String courseId);
}
