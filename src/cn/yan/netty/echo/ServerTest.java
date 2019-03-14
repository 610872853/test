package cn.yan.netty.echo;

public class ServerTest {
	public static void main(String[] args) throws Exception {
		EchoServer server = new EchoServer(10086);
		server.run();
	}
}
