package com.read.javaFile;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * Created by syy on 2017/1/18.
 */
public class Attachment {
    public AsynchronousServerSocketChannel channelServer;
    public AsynchronousSocketChannel channelClient;
    public boolean isReadMode;
    public ByteBuffer buffer;
    public SocketAddress clientAddr;
}
