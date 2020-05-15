package cn.cqut.final_edu_ketangpai.exception;

import java.io.Serializable;

/**
 * @CLASSNAME:CourseOperationException
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-11 14:14
 */
public class HomeworkOperationException extends RuntimeException implements Serializable {


	private static final long serialVersionUID = -972583661852638114L;

	public HomeworkOperationException(String msg) {
		super(msg);
	}
}
