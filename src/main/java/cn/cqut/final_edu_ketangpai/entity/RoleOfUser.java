package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoleOfUser implements Serializable {
	/**
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 *
	 */
	@TableField("user_id")
	private String userId;

	/**
	 *
	 */
	@TableId("role_id")
	private Integer roleId;


	private static final long serialVersionUID = 1L;
}

