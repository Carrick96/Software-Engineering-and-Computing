package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.dao.CourseDao;
import cn.cqut.final_edu_ketangpai.dao.CourseOfStudentDao;
import cn.cqut.final_edu_ketangpai.dto.CourseExecution;
import cn.cqut.final_edu_ketangpai.dto.CourseOfStudentExecution;
import cn.cqut.final_edu_ketangpai.entity.Course;
import cn.cqut.final_edu_ketangpai.entity.CourseOfStudent;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.enums.CourseStateEnum;
import cn.cqut.final_edu_ketangpai.exception.CourseOperationException;
import cn.cqut.final_edu_ketangpai.service.CourseOfStudentService;
import cn.cqut.final_edu_ketangpai.util.UserTool;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @CLASSNAME:CourseOfStudentServiceImpl
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-09 16:15
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CourseOfStudentServiceImpl extends ServiceImpl<CourseOfStudentDao, CourseOfStudent> implements CourseOfStudentService {
	@Autowired
	private CourseOfStudentDao courseOfStudentDao;
	@Autowired
	private CourseDao courseDao;

	@Override
	public CourseExecution selectCountOfStudent(String courseId) {
		Course course = courseDao.selectOne(new QueryWrapper<Course>()
				.lambda()
				.eq(Course::getCourseId, courseId));
		User currentUser = UserTool.getCurrentUser();
		if (currentUser == null) {
			return new CourseExecution(CourseStateEnum.INNER_ERROR);
		}
		CourseExecution courseExecution = null;
		assert course != null;
		int studentNum = courseOfStudentDao.getStudentNum(currentUser.getUserId(), course.getCourseId());
		assert false;
		courseExecution.setCount(studentNum);
		return courseExecution;
	}

	@Override
	public CourseOfStudentExecution getStudentNum(String courseId) {
		CourseOfStudentExecution courseOfStudentExecution = new CourseOfStudentExecution();
		Integer result = courseOfStudentDao.selectCount(new QueryWrapper<CourseOfStudent>()
				.lambda()
				.eq(CourseOfStudent::getCourseId, courseId));
		if (result <= 0) {
			throw new CourseOperationException("查询失败");
		}
		courseOfStudentExecution.setCount(result);
		return courseOfStudentExecution;
	}

	@Override
	public List<User> getStudentName(String courseId) {
		return courseOfStudentDao.getStuName(courseId);
	}

	@Override
	public CourseExecution studentJoinCourse(String studentId,String courseId) {
		int result = courseOfStudentDao.joinCourse(studentId, courseId);
		if (result <= 0) {
			return new CourseExecution(CourseStateEnum.NULL_COURSEID);
		}
		return new CourseExecution(CourseStateEnum.SUCCESS);
	}
}
