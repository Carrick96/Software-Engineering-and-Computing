package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.entity.RoleOfUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:RoleOfUserService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 19:51
 */
public interface RoleOfUserService extends IService<RoleOfUser> {
	int addRole(RoleOfUser item);

	RoleOfUser getUserRole(String userId, int roleId);
}
