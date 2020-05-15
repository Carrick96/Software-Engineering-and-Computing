package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:TeacherService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 14:09
 */
public interface TeacherService extends IService<Teacher> {
	int addTeacher(Teacher teacher);
}
