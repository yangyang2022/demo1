package com.read.javaFile;

import com.yangyang.util.StaticUtils;
import org.junit.Test;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class ClassicFileDemo {

    private String baseFilePath = "F:\\ideaCode\\demo1\\src\\main\\resources\\files\\abc.txt";

    private String baseSrcFilePath = "C:\\code\\data.txt";

    private String baseDir = "F:\\ideaCode\\demo1";


    @Test
    public void testFile1() throws IOException {

        File file = new File(baseFilePath);

        boolean b = file.setWritable(true);


        File absoluteFile = file.getAbsoluteFile();
        System.out.println(absoluteFile.getName() + " : " + absoluteFile.getPath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getFreeSpace() + " : " + file.getUsableSpace() + " : " + file.getTotalSpace());

        File afile  = new File("../temp");
        System.out.println(afile.isAbsolute() + " : " + afile.exists() + " : " + afile.isDirectory()+" : "+afile.lastModified());


    }

    @Test
    public void testFile2() {
        File[] files = File.listRoots();
        for(File f:files){
            System.out.println(f);
        }
    }

    @Test
    public void testFile3() {
        File[] files = new File(baseDir).listFiles(pathname -> pathname.getName().endsWith(".java"));
        System.out.println(files.length);

        StaticUtils.printArray(files);

    }

    @Test
    public void testFile4() throws IOException {

        System.out.println(System.getProperty("java.io.tmpdir"));

        File temp = File.createTempFile("text","txt");
        System.out.println(temp);


        temp.deleteOnExit();

    }

    // =============== RandomAccess =================
    @Test
    public void testRandomAcess() throws IOException {

        RandomAccessFile raf = new RandomAccessFile(baseFilePath, "rw");

        FileDescriptor fd = raf.getFD();
        raf.write("xxhello world".getBytes());
        fd.sync();
        raf.write("xxhello yangyangxxxxxxxsameedede".getBytes());

        raf.close();

    }

    //=============== out/inputStream =================
    @Test
    public void testStream() throws IOException {


        //OutputStream os = System.out;
        //os.write("12345 hello world".getBytes());
        //os.flush();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bao.write("hello yangyang".getBytes());

        ByteArrayInputStream bain = new ByteArrayInputStream(bao.toByteArray());

        while (bain.available() > 0 ){
            System.out.print(((char) bain.read()));
        }
        System.out.println();
    }

    @Test
    public void testFileOutputStream() throws IOException {

        //OutputStream fout = new BufferedOutputStream(new FileOutputStream(baseFilePath,true));
        //fout.write("--> hello world <-- ".getBytes());
        //fout.close();

        DataOutputStream dos = new DataOutputStream(new FileOutputStream(baseFilePath,true));
        DataInputStream din = new DataInputStream(new FileInputStream(baseFilePath));
        dos.writeUTF("\r\n====hello yangyang====");
        dos.writeDouble(2.345);
        dos.writeBoolean(true);
        dos.close();

        //String s = din.readLine();
        //System.out.println(s);
        //din.close();
    }

    @Test
    public void testWriterReader() throws IOException {

        //InputStreamReader isr = new InputStreamReader(new FileInputStream(baseFilePath));
        //while (isr.ready()){
        //    System.out.print((char) isr.read());
        //}

        String[] lines =
                {
                        "It was the best of times, it was the worst of times,",
                        "it was the age of wisdom, it was the age of foolishness,",
                        "it was the epoch of belief, it was the epoch of incredulity,",
                        "it was the season of Light, it was the season of Darkness,",
                        "it was the spring of hope, it was the winter of despair."
                };

        BufferedWriter br = new BufferedWriter(new FileWriter(baseFilePath));
        for(String line : lines){
            br.write(line,0,line.length());
            br.newLine();
        }
        br.close();
        BufferedReader brr = new BufferedReader(new FileReader(baseFilePath));
        String s = "";
        while ((s = brr.readLine()) != null){
            System.out.println(s);
        }
        brr.close();
    }

    @Test
    public void testByteBuffer() {

        //ByteBuffer buffer = ByteBuffer.allocate(1024);
        //System.out.println(buffer.limit() + " : " + buffer.mark() + " : " + buffer.position() + " : " + buffer.capacity());

        //ByteBuffer rbf = buffer.asReadOnlyBuffer();
        //ByteBuffer df = buffer.duplicate();
        //buffer.put("hello world".getBytes());

        //for (int i = 0; i < buffer.position(); ++i) {
        //    System.out.print((char) df.get(i));
        //}
        //buffer.limit(buffer.position()).position(0);
        //System.out.println(buffer.remaining());

        //byte[] array = df.array();
        //for (int i = 0; i < array.length; ++i) {
        //    System.out.print((char)array[i]);
        //}
        //System.out.println();


        String[] poem =
                {
                        "Roses are red",
                        "Violets are blue",
                        "Sugar is sweet",
                        "And so are you."
                };

        CharBuffer buffer = CharBuffer.allocate(50);
        for (int i = 0; i < poem.length; ++i) {
            //fill buffer
            for (int j = 0; j < poem[i].length(); ++j) {
                buffer.put(poem[i].charAt(j));
            }
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.print(buffer.get());
            }
            buffer.clear();
            System.out.println();
        }
    }

    @Test
    public void testBuffer() {
        IntBuffer buffer = IntBuffer.allocate(7);
        buffer.put(10).put(20).put(30).put(40);
        System.out.println(buffer.limit()+" : "+buffer.position()+" : "+buffer.capacity());
        buffer.limit(4);
        buffer.position(1).mark().position(3);
        System.out.println("get: "+buffer.get());

        buffer.clear();
        //System.out.println("reset get: "+buffer.get());
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
        System.out.println(buffer.equals(buffer));

        //int i = Byte.compare((byte) 12,(byte) 14);
        //System.out.println(i);

    }


    @Test
    public void testChannel() throws IOException {
        //ByteBuffer buffer = ByteBuffer.allocate(1024);
        //buffer.asCharBuffer().put("---> hello world");
        //
        //FileOutputStream fos = new FileOutputStream(baseFilePath);
        //FileChannel foutChannel = fos.getChannel();
        //foutChannel.write(buffer);
        //
        //foutChannel.close();
        //fos.close();
        //FileChannel fin = new FileInputStream(baseFilePath).getChannel();

        //while ((fin.read(buffer))!=-1){
        //    System.out.print(buffer.get());
        //}

        //FileChannel fin = new FileInputStream(baseSrcFilePath).getChannel();
        //FileChannel fout = new FileOutputStream(baseFilePath).getChannel();

        //fin.transferTo(0,fin.size(),fout);
        //fout.transferFrom(fin,0,fin.size());

        //FileLock lock = fin.lock();
        //lock.release();

        //MappedByteBuffer mbf = fin.map(FileChannel.MapMode.READ_WRITE, 50, 100);

        FileChannel rbc = new FileInputStream(baseFilePath).getChannel();
        WritableByteChannel wbc = Channels.newChannel(System.out);
        rbc.transferTo(0,rbc.size(),wbc);

    }

    @Test
    public void testSocketChannel() throws IOException {

        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        while (true){
            int numReadyChannels = selector.select();
            if(numReadyChannels == 0 ) continue;

            Set<SelectionKey> keys = selector.keys();
            Iterator<SelectionKey> keyIterator = keys.iterator();

            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = server.accept();
                    if(client == null) continue;
                    client.configureBlocking(false);//must be false
                    client.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel client1 = (SocketChannel) selectionKey.channel();
                    //read to client
                }else if(selectionKey.isWritable()){
                    SocketChannel client2 = (SocketChannel) selectionKey.channel();
                }
                keyIterator.remove();
            }
        }


    }
}
