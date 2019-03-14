package cn.yan.netty.test;

public class ClientTest {
	public static void main(String[] args) throws Exception {
		Client client = new Client("127.0.0.1", 10086);
		client.start();
	}
}
