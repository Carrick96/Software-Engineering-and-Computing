package cn.cqut.final_edu_ketangpai.dao;

import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @CLASSNAME:HomeWorkDao
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 16:42
 */
public interface HomeWorkDao extends BaseMapper<Homework> {

	@Select("select h.* from homework h,homework_of_student hos where h.homework_id = hos.homework and hos.student_id = #{studentId} and hos.course_id = #{courseId}")
	List<Homework> getStuHomeworkList(String studentId,String courseId);

	@Select("select user.* from user,course_of_student hos where hos.course_id = #{courseId} and user.user_id = hos.student_id and user.role_id = 2; ")
	List<User> selectStuListOfHomework(String courseId);
}
