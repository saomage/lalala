package com.hfut.rpc.myrpc2.Exception;

public class NoPrevException extends RuntimeException{
	
	public NoPrevException(Class clazz) {
		super(clazz.getName()+"没有前置节点");
	}

}
