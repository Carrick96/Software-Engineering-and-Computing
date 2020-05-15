package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("homework")
public class Homework implements Serializable {
	/**
	 * 默认主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 作业名称
	 */
	@TableField("title")
	private String title;

	/**
	 * 作业详情
	 */
	@TableField("detail")
	private String detail;

	/**
	 * 截止日期
	 */
	@TableField("deadline")
	private Date deadline;

	/**
	 *
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	/**
	 * 延迟提交标识
	 */
	@TableField("is_delay")
	private Boolean isDelay;

	/**
	 * 课程所属作业
	 */
	@TableField("course_id")
	private String courseId;

	/**
	 * 修改日期
	 */
	@TableField("modify_date")
	private LocalDateTime modifyDate;

	/**
	 *
	 */
	@TableField("delete_status")
	@TableLogic
	private Boolean deleteStatus;

	private static final long serialVersionUID = 1L;
}

