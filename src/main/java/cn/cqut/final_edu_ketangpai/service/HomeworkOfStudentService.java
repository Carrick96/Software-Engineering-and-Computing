package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.HomeworkExecution;
import cn.cqut.final_edu_ketangpai.dto.HomeworkOfStudentExecution;
import cn.cqut.final_edu_ketangpai.dto.ImageHolder;
import cn.cqut.final_edu_ketangpai.entity.HomeworkOfStudent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:HomeworkOfStudenfService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-19 18:59
 */
public interface HomeworkOfStudentService extends IService<HomeworkOfStudent> {
	/**
	 * 得到当前学生当前课程的作业
	 *
	 * @param courseId
	 * @param studentId
	 * @return
	 */
	HomeworkExecution getStuHomeworkList(String courseId, String studentId);

	/**
	 * 提交作业，包括对图片的处理
	 *
	 * @param homeworkOfStudent
	 * @param thumbnail
	 * @return
	 */
	HomeworkOfStudentExecution submitHomework(HomeworkOfStudent homeworkOfStudent, ImageHolder thumbnail);

}
