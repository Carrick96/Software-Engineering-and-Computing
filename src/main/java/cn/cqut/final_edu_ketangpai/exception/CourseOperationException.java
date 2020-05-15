package cn.cqut.final_edu_ketangpai.exception;

import java.io.Serializable;

/**
 * @CLASSNAME:CourseOperationException
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-11 14:14
 */
public class CourseOperationException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 3888839645934452820L;
	public CourseOperationException(String msg) {
		super(msg);
	}
}
