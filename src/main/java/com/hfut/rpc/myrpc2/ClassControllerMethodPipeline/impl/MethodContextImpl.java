package com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.impl;

import java.lang.reflect.Method;

import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.AbstructMethodContext;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodContext;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipeline;

import io.netty.channel.ChannelHandlerContext;

public class MethodContextImpl extends AbstructMethodContext{

	public MethodContextImpl(MethodPipeline pipeline, int order) {
		super(pipeline, order);
	}
	
	public MethodContextImpl(MethodPipeline pipeline, Method method) {
		super(pipeline, method);
	}

	@Override
	public void methodInvoke(ChannelHandlerContext ctx, Object instance) {
		//TODO
		if(ctx.channel().eventLoop().inEventLoop()) {
			
		}
	}

}
