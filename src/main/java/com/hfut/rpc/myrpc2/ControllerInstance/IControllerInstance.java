package com.hfut.rpc.myrpc2.ControllerInstance;

import io.netty.channel.ChannelHandlerContext;

public interface IControllerInstance {

    void invokeController(String json,ChannelHandlerContext ctx,Object...objects);
    
}
