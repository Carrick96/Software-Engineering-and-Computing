package cn.cqut.final_edu_ketangpai.dao;

import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.entity.HomeworkOfTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @CLASSNAME:HomeworkOfStudentDao
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-19 18:58
 */
public interface HomeworkOfTeacherDao extends BaseMapper<HomeworkOfTeacher> {

	@Select("select distinct h.* from homework h,homework_of_teacher hot,user ,course where h.homework_id = hot.homework_id and  hot.teacher_id = #{teacherId} and user.role_id = 1 and course.course_id = h.course_id order by h.id desc")
	List<Homework> getTeaHomeworks(@Param("teacherId") String teacherId);

	/**
	 * 查询未交作业人数
	 * @param courseId
	 * @return
	 */
	@Select("select count(hos.student_id) from homework h,homework_of_student hos where h.homework_id = hos.homework_id and h.course_id = #{courseId} and is_submit = 1")
	int getNoSubmitCount(String courseId);

	/**
	 * 查询已交作业人数
	 * @param courseId
	 * @return
	 */
	@Select("select count(hos.student_id) from homework h,homework_of_student hos where h.homework_id = hos.homework_id and h.course_id = #{courseId} and is_submit = 0")
	int getSubmitedCount(String courseId);

	/**
	 * 查询未批改作业数
	 * @param courseId
	 * @return
	 */
	@Select("select count(hos.student_id) from homework h,homework_of_student hos where h.homework_id = hos.homework_id and h.course_id = #{courseId} and is_read = 1")
	int getNoReadCount(String courseId);
}
