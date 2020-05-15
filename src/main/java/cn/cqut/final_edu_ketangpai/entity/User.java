package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User implements Serializable {
	/**
	 *
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 学号或职工号
	 */
	@TableId("user_id")
	private String userId;

	/**
	 * 密码
	 */
	@TableId("password")
	private String password;

	/**
	 * 登录账号，即邮箱
	 */
	@TableId("account")
	private String account;

	/**
	 * 用户名
	 */
	@TableField("username")
	private String username;

	/**
	 * 学校名称
	 */
	@TableField("school_name")
	private String schoolName;

	/**
	 * 逻辑删除字段
	 */
	@TableLogic
	@TableField("delete_status")
	private Boolean deleteStatus;

	/**
	 *
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	@TableField("role_id")
	 private Integer roleId;

	private static final long serialVersionUID = 1L;
}

