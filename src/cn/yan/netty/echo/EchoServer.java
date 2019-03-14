package cn.yan.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	private int port;
	
	public EchoServer(int port) {
		this.port = port;
	}
	
	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		
		try{
			ServerBootstrap bootstrap = new ServerBootstrap();
			//绑定线程池
			bootstrap.group(group);
			//指定使用的channel
			bootstrap.channel(NioServerSocketChannel.class);
			//监听端口
			bootstrap.localAddress(port);
			//绑定客户端连接操作
			bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					System.out.println("客户端：" + channel.remoteAddress() + "  已连接");
					channel.pipeline().addLast(new EchoServerHandler());   //客户端触发操作
				}
			});
			
			ChannelFuture channelFuture = bootstrap.bind().sync();   //客户端异步创建绑定
			System.out.println("服务器正在监听 " + channelFuture.channel().localAddress() + " 端口");
			channelFuture.channel().closeFuture().sync();          //关闭服务器资源
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
