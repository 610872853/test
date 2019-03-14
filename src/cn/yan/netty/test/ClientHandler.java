package cn.yan.netty.test;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ClientHandler extends ChannelInboundHandlerAdapter{

	private final static String CLASS_NAME = ClientHandler.class.getName() + ": ";
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(CLASS_NAME + "channelActive()");
		ctx.writeAndFlush(Unpooled.copiedBuffer("你好！我是" + ctx.channel().localAddress(), CharsetUtil.UTF_8));
		//发送后关闭连接
		ctx.close();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		System.out.println(CLASS_NAME + "channelRead()");
		
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println(CLASS_NAME + "channelReadComplete()");
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println(CLASS_NAME + "channelRegistered()");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println(CLASS_NAME + cause.getMessage());
		ctx.close();
	}
	

	
	
	
}
