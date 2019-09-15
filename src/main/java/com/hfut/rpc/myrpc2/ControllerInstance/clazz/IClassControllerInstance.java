package com.hfut.rpc.myrpc2.ControllerInstance.clazz;

import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipeline;
import com.hfut.rpc.myrpc2.ControllerInstance.IControllerInstance;

public interface IClassControllerInstance extends IControllerInstance{
	
	void setMethodPipeline(MethodPipeline pipeline);

}
