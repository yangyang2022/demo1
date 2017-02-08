package com.read.javaFile;

import com.yangyang.util.SleepUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class TestSocketServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8888));
        String msg = "Local address: "+ssc.socket().getLocalSocketAddress();

        ByteBuffer buffer  = ByteBuffer.wrap(msg.getBytes(Charset.defaultCharset()));

        int counter = 1;
        while (true){
            counter++;
            System.out.print(counter%10 != 0 ? "." : "\n");
            SocketChannel sc = ssc.accept();
            if(sc != null){
                System.out.println("");
                System.out.println("Receive msg from: "+sc.socket().getRemoteSocketAddress());
                //buffer.rewind();//like reset ....
                sc.write(ByteBuffer.wrap(msg.getBytes()));
                sc.close();
            }else {
                SleepUtils.sleep1Second();
            }
        }
    }
}
