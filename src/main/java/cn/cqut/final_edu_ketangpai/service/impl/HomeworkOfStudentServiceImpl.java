package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.dao.HomeworkOfStudentDao;
import cn.cqut.final_edu_ketangpai.dto.HomeworkExecution;
import cn.cqut.final_edu_ketangpai.dto.HomeworkOfStudentExecution;
import cn.cqut.final_edu_ketangpai.dto.ImageHolder;
import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.entity.HomeworkOfStudent;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.enums.HomeworkStateEnum;
import cn.cqut.final_edu_ketangpai.exception.UserOperationException;
import cn.cqut.final_edu_ketangpai.service.HomeworkOfStudentService;
import cn.cqut.final_edu_ketangpai.util.FileUtil;
import cn.cqut.final_edu_ketangpai.util.PathUtil;
import cn.cqut.final_edu_ketangpai.util.UserTool;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @CLASSNAME:HomeworkOfStudentServiceImpl
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-19 19:07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HomeworkOfStudentServiceImpl extends ServiceImpl<HomeworkOfStudentDao, HomeworkOfStudent> implements HomeworkOfStudentService {
	@Autowired
	private HomeworkOfStudentDao homeworkOfStudentDao;

	@Override
	public HomeworkExecution getStuHomeworkList(String courseId, String studentId) {
		HomeworkExecution execution = new HomeworkExecution();
		List<Homework> stuHomeworkList = homeworkOfStudentDao.getStuHomeworkList(courseId, studentId);
		execution.setHomeworkList(stuHomeworkList);

		return null;
	}

	@Override
	public HomeworkOfStudentExecution submitHomework(HomeworkOfStudent homeworkOfStudent, ImageHolder thumbnail) {
		if (homeworkOfStudent == null || homeworkOfStudent.getHomeworkId() == null) {
			return new HomeworkOfStudentExecution(HomeworkStateEnum.NULL_HOMEWORK);
		}
		User currentUser = UserTool.getCurrentUser();
		if (currentUser == null) {
			throw new UserOperationException("当前session已失效");
		}
		//1 处理图片，删除原来的图片
		HomeworkOfStudent tempHomework = homeworkOfStudentDao.selectOne(new QueryWrapper<HomeworkOfStudent>()
				.eq("homework_id", homeworkOfStudent.getHomeworkId())
				.eq("student_id", currentUser.getUserId()));
		FileUtil.deleteFileOrPath(tempHomework.getFileLink());
		addFile(homeworkOfStudent, thumbnail);
		homeworkOfStudent.setModifyTime(LocalDateTime.now());
		int result = homeworkOfStudentDao.update(homeworkOfStudent, new QueryWrapper<>());
		if (result <= 0) {
			return new HomeworkOfStudentExecution(HomeworkStateEnum.INNER_ERROR);
		} else {
			HomeworkOfStudent homework = homeworkOfStudentDao.selectOne(new QueryWrapper<HomeworkOfStudent>()
					.eq("homework_id", homeworkOfStudent.getHomeworkId())
					.eq("student_id", currentUser.getUserId()));
			return new HomeworkOfStudentExecution(HomeworkStateEnum.SUCCESS, homework);
		}
	}

	private void addFile(HomeworkOfStudent homeworkOfStudent, ImageHolder thumbnail) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(homeworkOfStudent.getHomeworkId(), homeworkOfStudent.getStudentId());
		String fileAddr = FileUtil.generateThumbnail(thumbnail, dest);
		homeworkOfStudent.setFileLink(fileAddr);
	}
}
