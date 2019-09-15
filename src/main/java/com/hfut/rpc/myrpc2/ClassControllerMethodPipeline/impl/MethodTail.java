package com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.impl;

import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.AbstructMethodContext;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodContext;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipeline;
import com.hfut.rpc.myrpc2.ControllerInstance.clazz.WriteBack;
import com.hfut.rpc.myrpc2.Exception.NoNextException;

import io.netty.channel.ChannelHandlerContext;

public class MethodTail extends AbstructMethodContext{
	
	WriteBack writeBack;
	
	public MethodTail(MethodPipeline pipeline) {
		super(pipeline, Integer.MAX_VALUE);
	}

	@Override
	public void methodInvoke(ChannelHandlerContext ctx,Object instance) {
		//TODO
	}

	@Override
	public void setNext(MethodContext methodContext) {
		throw new RuntimeException("头节点无法设置后置节点");
	}

	@Override
	public AbstructMethodContext getNext() {
		throw new NoNextException(this.getClass());
	}
	
	public void linkHeadTail(MethodContext head) {
		this.setPrev(head);
	}
	
	public void setWriteBack(WriteBack writeBack) {
		this.writeBack=writeBack;
	}

}
