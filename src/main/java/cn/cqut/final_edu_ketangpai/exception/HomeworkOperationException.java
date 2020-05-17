package cn.cqut.final_edu_ketangpai.exception;

import java.io.Serializable;

/**
 * @CLASSNAME:HomeworkOperationException
 * @description:
 * @author: Nonameguy
 * @create: 2020-05-15 16:47
 */
public class HomeworkOperationException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 2478797870446688269L;

	public HomeworkOperationException(String msg) {
		super(msg);
	}
}
