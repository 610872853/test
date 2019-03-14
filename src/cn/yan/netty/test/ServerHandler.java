package cn.yan.netty.test;

import java.nio.charset.Charset;

import sun.java2d.pipe.BufferedBufImgOps;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter{
	private final static String CLASS_NAME = ServerHandler.class.getName() + ": ";
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(CLASS_NAME + "channelActive()");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(CLASS_NAME + "channelRead()");
		ByteBuf buf = (ByteBuf) msg;
		System.out.println("客户端原始消息：" + msg);
		System.out.println("客户端发送过来的消息：" + buf.readBytes(buf.readableBytes()).toString(Charset.forName("utf-8")));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println(CLASS_NAME + "channelReadComplete()");
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println(CLASS_NAME + "channelRegistered()");
		System.out.println("客户端" + ctx.channel().remoteAddress() + "已连接");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println(CLASS_NAME + cause.getMessage());
		ctx.close();
	}
	
	
	
}
