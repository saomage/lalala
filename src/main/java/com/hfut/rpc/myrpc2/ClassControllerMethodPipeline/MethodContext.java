package com.hfut.rpc.myrpc2.ClassControllerMethodPipeline;

import com.hfut.rpc.myrpc2.Util.ObjectUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public interface MethodContext {
	
	void methodInvoke(ChannelHandlerContext ctx,Object instance);
	
	MethodPipeline pipeline();
	
	int getOrder();
	
	void setNext(MethodContext next);
	
	void setPrev(MethodContext prev);
	
	MethodContext getNext();
	
	MethodContext getPrev();
	
	void insertPrev(MethodContext methodContext);
}
