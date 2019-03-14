package cn.yan.netty.test;

public class ServerTest {
	public static void main(String[] args) {
		Server server = new Server(10086);
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
