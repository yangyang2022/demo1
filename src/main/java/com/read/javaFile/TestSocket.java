package com.read.javaFile;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class TestSocket {
    public static void main(String[] args) throws IOException {

        SocketChannel sc = SocketChannel.open(new InetSocketAddress("localhost",9999));

        //System.out.println(sc.finishConnect()+" : "+sc.isConnected());

        sc.configureBlocking(false);

        while (!sc.finishConnect()){
            System.out.println("waiting for connect ... ");
        }
        //SleepUtils.sleep1Second();

        long time = 0;
        ByteBuffer buffer = ByteBuffer.allocate(8);
        while (sc.read(buffer) > 0){
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.print(new Date((long)buffer.get()));
            }
            buffer.clear();
        }
        sc.close();

    }
}
