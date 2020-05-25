package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.HomeworkExecution;
import cn.cqut.final_edu_ketangpai.entity.HomeworkOfTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:HomeworkOfTeacherService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-22 21:38
 */
public interface HomeworkOfTeacherService extends IService<HomeworkOfTeacher> {
	HomeworkExecution getTeaHomeworkList(String teacherId);

	int getNoSubmitCount(String courseId);

	int getSubmitedCount(String courseId);

	int getNoReadCount(String courseId);
}
