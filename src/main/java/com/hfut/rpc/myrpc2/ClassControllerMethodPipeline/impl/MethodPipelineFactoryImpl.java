package com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.impl;

import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipeline;
import com.hfut.rpc.myrpc2.ClassControllerMethodPipeline.MethodPipelineFactory;

public class MethodPipelineFactoryImpl implements MethodPipelineFactory{

	@Override
	public MethodPipeline create() {
		return new MethodPipelineImpl();
	}

}
