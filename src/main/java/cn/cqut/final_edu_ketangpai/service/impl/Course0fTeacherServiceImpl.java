package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.dao.CourseDao;
import cn.cqut.final_edu_ketangpai.dao.CourseOfTeacherDao;
import cn.cqut.final_edu_ketangpai.dto.CourseExecution;
import cn.cqut.final_edu_ketangpai.entity.CourseOfTeacher;
import cn.cqut.final_edu_ketangpai.enums.CourseStateEnum;
import cn.cqut.final_edu_ketangpai.service.Course0fTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CLASSNAME:Course0fTeacherServiceImpl
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-10 14:03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class Course0fTeacherServiceImpl extends ServiceImpl<CourseOfTeacherDao, CourseOfTeacher> implements Course0fTeacherService {
	@Autowired
	private CourseOfTeacherDao courseOfTeacherDao;
	@Autowired
	private CourseDao courseDao;

	@Override
	public CourseExecution joinCourse(String teacherId, String courseId) {
		int result = courseOfTeacherDao.joinCourse(teacherId, courseId);
		if (result <= 0) {
			return new CourseExecution(CourseStateEnum.NULL_COURSEID);
		}
		return new CourseExecution(CourseStateEnum.SUCCESS);
	}
}
