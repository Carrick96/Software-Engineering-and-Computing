package cn.cqut.final_edu_ketangpai.exception;

import java.io.Serializable;

/**
 * @CLASSNAME:CourseOperationException
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-11 14:14
 */
public class CourseOfStudentOperationException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -8233705676133566666L;

	public CourseOfStudentOperationException(String msg) {
		super(msg);
	}
}
