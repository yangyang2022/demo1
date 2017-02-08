package com.read.javaFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class TestChanel {

    private static void copy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while ((src.read(buffer)) !=-1){
            buffer.flip();
            while (buffer.hasRemaining()) dest.write(buffer);
            buffer.clear();
        }
    }

    public static void main(String[] args) throws IOException {

        ReadableByteChannel readChanel = Channels.newChannel(System.in);
        WritableByteChannel writeChannel = Channels.newChannel(System.out);

        copy(readChanel,writeChannel);

    }
}
