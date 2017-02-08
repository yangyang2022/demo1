package com.read.javaFile;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Formatter;
import java.util.stream.Collectors;

public class TestRegExpressions {

    @Test
    public void testReg1() {
        String s = "java hello world";
        System.out.println(s.matches("(.+?)"));
    }

    @Test
    public void testCharset() {

        System.out.println(Integer.toBinaryString(255));

        CharBuffer buffer = CharBuffer.wrap("hello world");
        while (buffer.hasRemaining()){
            System.out.print((char) buffer.get());
        }
        System.out.println();

    }

    @Test
    public void testCharset2() throws UnsupportedEncodingException {
        byte[] encodedMsg = {
                        0x66, 0x61, (byte) 0xc3, (byte) 0xa7, 0x61, 0x64, 0x65, 0x20, 0x74,
                        0x6f, 0x75, 0x63, 0x68, (byte) 0xc3, (byte) 0xa9 };
        String s = new String(encodedMsg,"utf-8");
        System.out.println(s);
        System.out.println("hello world".toCharArray());

        //System.out.println(Integer.toHexString((int) 'a'));

        String s1 = Arrays.stream("hello world".split(""))
                .map(e -> e.charAt(0))
                .map(i -> Integer.toHexString((int) i))
                .collect(Collectors.joining(",", "{", "}"));
        System.out.println(s1);

        byte[] msg = {0x68,0x65,0x6c,0x6c,0x6f,0x20,0x77,0x6f,0x72,0x6c,0x64};
        String ms = new String(msg,"utf-8");
        System.out.println(ms);

    }

    @Test
    public void testFormatter() {

        Formatter formatter = new Formatter();

        formatter.format("%1$d ,%1$d",123);

        System.out.println(formatter.toString());


    }
}
