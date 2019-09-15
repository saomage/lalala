package com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.impl;

import java.lang.reflect.Method;

import com.hfut.rpc.myrpc2.Annotation.MethodChain;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodContext;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipeline;
import com.hfut.rpc.myrpc2.ControllerInstance.IControllerInstance;
import com.hfut.rpc.myrpc2.ControllerInstance.clazz.IClassControllerInstance;
import com.hfut.rpc.myrpc2.ControllerInstance.clazz.WriteBack;

import io.netty.channel.ChannelHandlerContext;

public class MethodPipelineImpl implements MethodPipeline{
	
	MethodHead head=new MethodHead(this);
	
	MethodTail tail=new MethodTail(this);
	
	IClassControllerInstance instance;

	@Override
	public void addMehtod(Method method) {
		 MethodContext methodContext = new MethodContextImpl(this, method);
		 head.insertPrev(methodContext);
	}

	@Override
	public void removeMethod(Method method) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setControllerInstance(IClassControllerInstance controllerInstance) {
		this.instance=controllerInstance;
		controllerInstance.setMethodPipeline(this);
	}

	@Override
	public IClassControllerInstance getControllerInstance() {
		return instance;
	}

	@Override
	public void invoke(ChannelHandlerContext ctx, Object instance, WriteBack wb) {
		tail.setWriteBack(wb);
		head.methodInvoke(ctx, instance);
	}

}
