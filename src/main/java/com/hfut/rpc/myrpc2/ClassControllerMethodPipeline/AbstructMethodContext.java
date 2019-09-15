package com.hfut.rpc.myrpc2.ClassControllerMethodPipeline;

import java.lang.reflect.Method;

import com.hfut.rpc.myrpc2.Annotation.MethodChain;
import com.hfut.rpc.myrpc2.Util.ObjectUtil;

import io.netty.channel.ChannelHandlerContext;

public abstract class AbstructMethodContext implements MethodContext{
	
	private MethodPipeline pipeline;
	
	private MethodContext prev;
	
	protected MethodContext next;
	
	private int order=0;
	
	protected Method method;

	@Override
	public abstract void methodInvoke(ChannelHandlerContext ctx,Object instance) ;
	
	public AbstructMethodContext(MethodPipeline pipeline,int order) {
		this.pipeline=pipeline;
		this.order=order;
	}
	
	public AbstructMethodContext(MethodPipeline pipeline,Method method) {
		this(pipeline,method.getAnnotation(MethodChain.class).order());
		this.method=method;
	}
	
	@Override
	public void insertPrev(MethodContext methodContext) {
		ObjectUtil.checkNotNull(methodContext, "methodContext为空");
		if(order>=methodContext.getOrder()) {
			ObjectUtil.checkNotNull(this.getPrev(), "不存在前置节点,不能添加前置节点");
			methodContext.setNext(this);
			methodContext.setPrev(prev);
			prev.setNext(methodContext);
			this.setPrev(methodContext);
		}else {
			next.insertPrev(methodContext);
		}
	}

	@Override
	public MethodPipeline pipeline() {
		return pipeline;
	}
	
	@Override
	public int getOrder() {
		return order;
	}
	
	@Override
	public void setNext(MethodContext next) {
		this.next=next;
	}
	
	@Override
	public void setPrev(MethodContext prev) {
		this.prev=prev;
	}
	
	@Override
	public MethodContext getNext() {
		return next;
	}
	
	@Override
	public MethodContext getPrev() {
		return prev;
	}

}
