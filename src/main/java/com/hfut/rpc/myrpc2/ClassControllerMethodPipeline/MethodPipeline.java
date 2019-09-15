package com.hfut.rpc.myrpc2.ClassControllerMethodPipeline;

import java.lang.reflect.Method;

import com.hfut.rpc.myrpc2.ControllerInstance.clazz.IClassControllerInstance;
import com.hfut.rpc.myrpc2.ControllerInstance.clazz.WriteBack;

import io.netty.channel.ChannelHandlerContext;

public interface MethodPipeline {

	void addMehtod(Method method);
	
	void removeMethod(Method method);
	
	void setControllerInstance(IClassControllerInstance controllerInstance);
	
	IClassControllerInstance getControllerInstance();
	
	void invoke(ChannelHandlerContext ctx,Object instance,WriteBack wb);

}
