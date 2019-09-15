package com.hfut.rpc.myrpc2.Exception;

public class ObjectNotNullException extends RuntimeException{
	
	public ObjectNotNullException(String s) {
		super(s+"不为空,无法重复添加");
	}

}
