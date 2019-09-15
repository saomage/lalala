package com.hfut.rpc.myrpc2.ControllerInstance.clazz;

import io.netty.channel.ChannelHandlerContext;

public interface WriteBack {
	
	boolean writeBack(Object result, ChannelHandlerContext ctx);

}
