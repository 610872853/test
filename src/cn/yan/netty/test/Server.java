package cn.yan.netty.test;

import java.net.SocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
	private int port;
	
	public Server(int port) {
		this.port = port;
	}
	
	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			System.out.println("服务器:");
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(group);
			bootstrap.channel(NioServerSocketChannel.class);
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					System.out.println("initChannel()");
					channel.pipeline().addLast(new ServerHandler());
				}
			});
			//执行
			ChannelFuture channelFuture = bootstrap.bind(port).sync();
			channelFuture.addListener(new ChannelFutureListener() {
				
				public void operationComplete(ChannelFuture future) throws Exception {
					System.out.println("operationComplete()");
					System.out.println(future.channel().isRegistered());
				}
			});
			System.out.println("监听端口：" + channelFuture.channel().localAddress());
			channelFuture.channel().closeFuture().sync();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
