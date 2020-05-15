package cn.cqut.final_edu_ketangpai.service.impl;

import cn.cqut.final_edu_ketangpai.dao.RoleOfUserDao;
import cn.cqut.final_edu_ketangpai.entity.RoleOfUser;
import cn.cqut.final_edu_ketangpai.service.RoleOfUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @CLASSNAME:RoleOfUserServiceImpl
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 19:52
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleOfUserServiceImpl  extends ServiceImpl<RoleOfUserDao, RoleOfUser> implements RoleOfUserService {
	@Autowired
	private RoleOfUserDao roleOfUserDao;

	@Override
	public RoleOfUser getUserRole(String userId, int roleId) {
		return roleOfUserDao.selectOne(new QueryWrapper<RoleOfUser>()
				.lambda()
				.eq(RoleOfUser::getUserId,userId)
				.eq(RoleOfUser::getRoleId,roleId));
	}

	@Override
	public int addRole(RoleOfUser item) {
		return roleOfUserDao.insert(item);
	}
}
