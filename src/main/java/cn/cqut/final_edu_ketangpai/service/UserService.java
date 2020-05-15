package cn.cqut.final_edu_ketangpai.service;

import cn.cqut.final_edu_ketangpai.dto.UserExecution;
import cn.cqut.final_edu_ketangpai.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @CLASSNAME:UserService
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-14 18:20
 */
public interface UserService extends IService<User> {
	UserExecution register(User user);

	UserExecution selectUser(String account, String password);
}
