package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("course_of_teacher")
public class CourseOfTeacher implements Serializable {
	private static final long serialVersionUID = 8714950475005874563L;
	/**
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 *
	 */
	@TableField("course_id")
	private String courseId;

	/**
	 *
	 */
	@TableField("teacher_id")
	private String teacherId;}
