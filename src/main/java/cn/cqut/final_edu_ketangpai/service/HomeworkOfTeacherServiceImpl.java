package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dao.HomeworkOfTeacherDao;
import cn.cqut.final_edu_ketangpai.dto.HomeworkExecution;
import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.entity.HomeworkOfTeacher;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CLASSNAME:HomeworkOfTeacherServiceImpl
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-22 21:45
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class HomeworkOfTeacherServiceImpl extends ServiceImpl<HomeworkOfTeacherDao, HomeworkOfTeacher>implements HomeworkOfTeacherService {
	@Autowired
	private HomeworkOfTeacherDao homeworkOfTeacherDao;
	@Override
	public HomeworkExecution getTeaHomeworkList(String teacherId) {
		HomeworkExecution homeworkExecution = new HomeworkExecution();
		List<Homework> homeworks = homeworkOfTeacherDao.getTeaHomeworks(teacherId);
		homeworkExecution.setHomeworkList(homeworks);
		return homeworkExecution;
	}

	@Override
	public int getNoSubmitCount(String courseId) {
		return homeworkOfTeacherDao.getNoSubmitCount(courseId);
	}

	@Override
	public int getSubmitedCount(String courseId) {
		return homeworkOfTeacherDao.getSubmitedCount(courseId);
	}

	@Override
	public int getNoReadCount(String courseId) {
		return homeworkOfTeacherDao.getNoReadCount(courseId);
	}
}
