package cn.cqut.final_edu_ketangpai.dao;

import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.entity.HomeworkOfStudent;
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
public interface HomeworkOfStudentDao extends BaseMapper<HomeworkOfStudent> {
	/**
	 * 得到当前学生当前课程的作业
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	@Select("select h.* from homework h,homework_of_student hos,user u where h.homework_id = hos.homework_id and h.course_id = #{courseId} and u.user_id = hos.student_id and u.user_id = #{studentId}")
	List<Homework> getStuHomeworkList(@Param("courseId") String courseId, @Param("studentId") String studentId);
}
