package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class HomeworkOfTeacher implements Serializable {
	/**
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 *
	 */
	@TableField("homework_id")
	private String homeworkId;

	/**
	 *
	 */
	@TableField("teacher_id")
	private String teacherId;

	private static final long serialVersionUID = 1L;
}

