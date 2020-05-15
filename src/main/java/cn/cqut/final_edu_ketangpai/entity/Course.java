package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("course")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course implements Serializable {
	/**
	 * 默认主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 课程编号
	 */
	@TableField("course_id")
	private String courseId;

	/**
	 * 课程名称
	 */
	@TableField("course_name")
	private String courseName;

	/**
	 *
	 */
	@TableField("class_name")
	private String className;

	/**
	 * 授课教师ID
	 */
	@TableField("teacher_id")
	private String teacherId;

	/**
	 * 授课教师姓名
	 */
	@TableField("teacher_name")
	private String teacherName;

	/**
	 *
	 */
	@TableField("year")
	private String year;

	/**
	 * 授课学期
	 */
	@TableField("term")
	private String term;

	/**
	 *
	 */
	@TableField("create_date")
	private LocalDateTime createDate;

	@TableField("modify_date")
	private LocalDateTime modifyDate;

	/**
	 *
	 */
	@TableLogic
	@TableField("delete_status")
	private Boolean deleteStatus;

	/**
	 * 是否置顶，0为不置顶，1为置顶
	 */
	@TableField("top_status")
	private Boolean topStatus;

	/**
	 * 归档状态，0为不归档，1为归档
	 */

	private Boolean archiveStatus;

	private static final long serialVersionUID = 1L;
}

