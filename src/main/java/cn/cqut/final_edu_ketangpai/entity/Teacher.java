package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("teacher")
public class Teacher implements Serializable {
	/**
	 * 自增id
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 职工号
	 */
	@TableField("teacher_id")
	private String teacherId;

	/**
	 * 教师姓名
	 */
	@TableField("teacher_name")
	private String teacherName;

	private static final long serialVersionUID = 1L;
}

