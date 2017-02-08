package com.read.javaFile;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectorServer {
    public static final int DEFAULT_PORT = 9999;
    private static ByteBuffer bb = ByteBuffer.allocate(8);

    public static void main(String[] args) throws IOException {

        System.out.println("server starting port ... "+DEFAULT_PORT);

        ServerSocketChannel ssc = ServerSocketChannel.open().bind(new InetSocketAddress(DEFAULT_PORT));
        ssc.configureBlocking(false);

        Selector selector  = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int num = selector.select();
            if(num == 0) continue;

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()){
                    SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
                    if(sc == null) continue;
                    System.out.println("recevie connection ... ");
                    bb.clear();
                    bb.putLong(System.currentTimeMillis());
                    bb.flip();
                    System.out.println("writing current time ... ");
                    while (bb.hasRemaining()) sc.write(bb);
                    sc.close();
                }
                keyIterator.remove();
            }
        }
    }
}
