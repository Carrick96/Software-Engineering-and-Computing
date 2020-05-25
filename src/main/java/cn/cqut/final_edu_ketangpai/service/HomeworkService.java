package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.HomeworkExecution;
import cn.cqut.final_edu_ketangpai.dto.UserExecution;
import cn.cqut.final_edu_ketangpai.entity.Homework;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:HomeworkService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 16:43
 */
public interface HomeworkService extends IService<Homework> {
	HomeworkExecution createHomework(Homework homework);

	UserExecution getStuListOfHomework(String courseId);

//	HomeworkExecution getStuHomeworks(String studentId,String courseId);
}
