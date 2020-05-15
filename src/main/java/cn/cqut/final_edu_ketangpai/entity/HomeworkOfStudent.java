package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class HomeworkOfStudent implements Serializable {
	/**
	 * 默认Id
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 作业ID
	 */
	@TableField("homework_id")
	private String homeworkId;

	/**
	 * 学生ID
	 */
	@TableId("student_id")
	private String studentId;

	/**
	 *
	 */
	@TableField("course_id")
	private String courseId;

	/**
	 * 作业文件链接
	 */
	@TableField("file_link")
	private String fileLink;

	/**
	 * 是否审阅
	 */
	@TableField("is_read")
	private Boolean isRead;

	/**
	 * 是否提交
	 */
	@TableField("is_submit")
	private Boolean isSubmit;

	/**
	 * 分数
	 */
	@TableField("socre")
	private String socre;

	/**
	 *
	 */
	@TableField("delete_status")
	@TableLogic
	private Boolean deleteStatus;

	/**
	 *
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	/**
	 *
	 */
	@TableField("modify_time")
	private LocalDateTime modifyTime;

	private static final long serialVersionUID = 1L;
}

