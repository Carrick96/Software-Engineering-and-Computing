package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.dao.HomeWorkDao;
import cn.cqut.final_edu_ketangpai.dto.HomeworkExecution;
import cn.cqut.final_edu_ketangpai.entity.Homework;
import cn.cqut.final_edu_ketangpai.enums.HomeworkStateEnum;
import cn.cqut.final_edu_ketangpai.exception.HomeworkOperationException;
import cn.cqut.final_edu_ketangpai.service.HomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CLASSNAME:HomeworkServiceImpl
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 16:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HomeworkServiceImpl extends ServiceImpl<HomeWorkDao,Homework> implements HomeworkService {
	@Autowired
	private HomeWorkDao homeWorkDao;
	@Override
	public HomeworkExecution createHomework(Homework homework) {
		if (homework == null) {
			throw new HomeworkOperationException("创建作业失败");
		}
		int result = homeWorkDao.insert(homework);
		if (result <= 0) {
			return new HomeworkExecution(HomeworkStateEnum.NULL_HOMEWORK);
		}
		return new HomeworkExecution(HomeworkStateEnum.SUCCESS);
	}
}
