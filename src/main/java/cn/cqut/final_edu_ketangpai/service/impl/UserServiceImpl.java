package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.dao.UserDao;
import cn.cqut.final_edu_ketangpai.dto.UserExecution;
import cn.cqut.final_edu_ketangpai.entity.User;
import cn.cqut.final_edu_ketangpai.exception.UserOperationException;
import cn.cqut.final_edu_ketangpai.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CLASSNAME:UserServiceImpl
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 18:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public UserExecution selectUser(String account, String password) {
		UserExecution userExecution = new UserExecution();
		User user = userDao.selectOne(new QueryWrapper<User>()
				.lambda()
				.eq(User::getAccount, account)
				.eq(User::getPassword, password));
		if (user == null) {
			throw new UserOperationException("账号或者密码错误");
		}
		assert false;
		userExecution.setUser(user);
		return userExecution;
	}

	@Override
	public UserExecution register(User user) {
		UserExecution userExecution = new UserExecution();
		int result = userDao.insert(user);
		if (result <= 0) {
			throw new UserOperationException("注册失败");
		}
		assert false;
		userExecution.setUser(user);
		return userExecution;
	}
}
