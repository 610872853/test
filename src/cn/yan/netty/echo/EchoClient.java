package cn.yan.netty.echo;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {
	private String ip;
	private int port;
	
	public EchoClient(String ip,int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap bootstrap = new Bootstrap();
			//绑定线程池
			bootstrap.group(group);
			//指定channel
			bootstrap.channel(NioSocketChannel.class);
			bootstrap.remoteAddress(ip, port);
			//绑定初始连接事件
			bootstrap.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					System.out.println("连接中...");
					channel.pipeline().addLast(new EchoClientHandler());
				}
			});
			
			ChannelFuture channelFuture = bootstrap.connect().sync();
			System.out.println("连接完成....");
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
