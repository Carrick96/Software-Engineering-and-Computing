package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.dao.TeacherDao;
import cn.cqut.final_edu_ketangpai.entity.Teacher;
import cn.cqut.final_edu_ketangpai.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CLASSNAME:TeacherServiceImpl
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 14:09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TeacherServiceImpl extends ServiceImpl<TeacherDao,Teacher>implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	@Override
	public int addTeacher(Teacher teacher) {
		return teacherDao.insert(teacher);
	}
}
