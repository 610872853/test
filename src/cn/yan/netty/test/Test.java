package cn.yan.netty.test;

import com.sun.xml.internal.ws.api.message.Packet;
import com.sun.xml.internal.ws.api.pipe.Pipe;
import com.sun.xml.internal.ws.api.pipe.PipeCloner;
import com.sun.xml.internal.ws.api.pipe.helper.AbstractPipeImpl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;

public class Test {
	public static void main(String[] args) {
		ByteBuf buf = Unpooled.copiedBuffer("Hello netty!!!", CharsetUtil.UTF_8);
		/*
		ByteBuf sliceBuf = buf.slice(0,3);
		System.out.println(buf.toString(CharsetUtil.UTF_8));
		System.out.println(sliceBuf.toString(CharsetUtil.UTF_8));
		buf.setByte(0,(byte)'J');
		System.out.println(buf.toString(CharsetUtil.UTF_8));
		System.out.println(sliceBuf.toString(CharsetUtil.UTF_8));
		*/
		/*
		System.out.println("read:" + buf.readerIndex());
		System.out.println("write:" + buf.writerIndex());
		
		System.out.println(buf.readBytes(10).toString(CharsetUtil.UTF_8));
		buf.writeInt(1);
		System.out.println("read:" + buf.readerIndex());
		System.out.println("write:" + buf.writerIndex());
		System.out.println("writeable:" + buf.writableBytes());
		*/
	}
}
