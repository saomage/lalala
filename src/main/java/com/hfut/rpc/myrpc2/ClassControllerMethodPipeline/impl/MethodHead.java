package com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.impl;

import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.AbstructMethodContext;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodContext;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipeline;
import com.hfut.rpc.myrpc2.Exception.NoPrevException;
import com.hfut.rpc.myrpc2.Util.ObjectUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class MethodHead extends AbstructMethodContext{
	
	public MethodHead(MethodPipeline pipeline) {
		super(pipeline,Integer.MIN_VALUE);
	}

	@Override
	public void methodInvoke(ChannelHandlerContext ctx,Object instance) {
		Channel channel = ctx.channel();
		ObjectUtil.checkNotNull(next, "后置节点为空");
		if(channel.eventLoop().inEventLoop()) {
			next.methodInvoke(ctx,instance);
		}else {
			channel.eventLoop().execute(()->{
				next.methodInvoke(ctx,instance);
			});
		}
	}
	
	public void linkHeadTail(MethodTail tail) {
		this.setNext(tail);
		tail.linkHeadTail(this);
	}

	@Override
	public void setPrev(MethodContext methodContext) {
		throw new RuntimeException("头节点无法设置前置节点");
	}

	@Override
	public MethodContext getPrev() {
		throw new NoPrevException(this.getClass());
	}

}
