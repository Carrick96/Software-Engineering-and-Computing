package cn.cqut.final_edu_ketangpai.dao;

import cn.cqut.final_edu_ketangpai.entity.CourseOfTeacher;
import cn.cqut.final_edu_ketangpai.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * @CLASSNAME:CourseOfTeacherDao
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-04 01:46
 */
public interface CourseOfTeacherDao extends BaseMapper<CourseOfTeacher> {
	@Insert("insert into course_of_teacher (course_id,teacher_id) " +
			"values ((select course_id from course where course_id = #{courseId}),#{teacherId}) ")
	int joinCourse(String teacherId, String courseId);

	List<User> getTeacherName(String courseId);
}
