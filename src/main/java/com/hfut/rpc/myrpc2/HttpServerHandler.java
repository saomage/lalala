package com.hfut.rpc.myrpc2;

import io.netty.buffer.CompositeByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        HttpHeaders headers = msg.headers();
        CompositeByteBuf content = (CompositeByteBuf)msg.content();

//        content.
    }
}
