package cn.cqut.final_edu_ketangpai.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class CourseOfStudent implements Serializable {

	private static final long serialVersionUID = 8603261109095458655L;
	/**
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 学生具体上的课
	 */
	@TableField("course_id")
	private String courseId;

	/**
	 * 具体的学生
	 */
	@TableField("student_id")
	private String studentId;
}

