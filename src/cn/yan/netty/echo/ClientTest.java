package cn.yan.netty.echo;

public class ClientTest {
	public static void main(String[] args) throws Exception {
		EchoClient client = new EchoClient("127.0.0.1", 10086);
		client.run();
		System.out.println("Test");
	}
}
