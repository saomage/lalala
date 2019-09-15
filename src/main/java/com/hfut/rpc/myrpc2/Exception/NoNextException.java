package com.hfut.rpc.myrpc2.Exception;

public class NoNextException extends RuntimeException{
	
	public NoNextException(Class clazz) {
		super(clazz.getName()+"没有后置节点");
	}

}
