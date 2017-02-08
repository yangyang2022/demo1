package com.read.javaFile;

import com.yangyang.util.SleepUtils;
import com.yangyang.util.StaticUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestNIO2File {
    private static final String basePath = "F:\\ideaCode\\demo1\\src\\main\\resources\\files\\abc.txt";
    private static final String baseDirPath = "F:\\ideaCode\\demo1\\src\\main\\resources\\files";
    private static final String newTempFilePath = "F:\\ideaCode\\demo1\\src\\main\\resources\\files\\temp.txt";
    private static final String tempDirPath = "F:\\ideaCode\\demo1\\src\\main\\resources\\files\\";
    private static final String baseDosDir = "C:\\code";

    @Test
    public void testDemo1() {

        FileSystem fsDefault = FileSystems.getDefault();
        System.out.println(fsDefault.getRootDirectories());

        FileSystemProvider.installedProviders().forEach(System.out::println);
    }

    @Test
    public void testFile1() {

        //File file = new File(basePath);
        //Path path = file.toPath();
        //
        //System.out.println(path.getFileName()+" : "+
        //        FilenameUtils.getExtension(path.getFileName().toString()));
        //System.out.println(path.getNameCount());

        FileSystem fs = FileSystems.getDefault();
        Path path = fs.getPath("a", "b", "c");
        System.out.println("count: "+path.getNameCount()+" -> "+path.getFileName());
        for (int i = 0; i < path.getNameCount(); ++i) {
            System.out.println(path.getName(i));
        }
        System.out.println("");
        System.out.println("parant: "+path.getParent());
        System.out.println("root: "+path.getRoot());
        System.out.println("subPath[0-2]: "+path.subpath(0,2));
        System.out.println("isAbsolute: "+path.isAbsolute());

        path = path.toAbsolutePath();

        System.out.println(path.isAbsolute()+" -> "+path.toString());

    }

    @Test
    public void testFile2() throws IOException {
        Path path1 = Paths.get("reports", ".", "2016", "jan");
        System.out.println(path1);
        System.out.println(path1.normalize());

        path1 = Paths.get("reports","2016","..","jan");
        System.out.println(path1.normalize());
        System.out.println();
        path1 = Paths.get("reports","2016","jan");
        System.out.println("reletive: "+path1.relativize(Paths.get("reports", "2017", "mar")));

        //Path rootPath = FileSystems.getDefault().getRootDirectories().iterator().next();
        //if(rootPath != null){
        //    System.out.printf("Root: %s%n",rootPath.toString());
        //    Path path = Paths.get(rootPath.toString(), "reports", "2017");
        //    System.out.println("path: " + path);
        //    System.out.println(path1.relativize(path));
        //}

        System.out.println(path1.toRealPath(LinkOption.NOFOLLOW_LINKS));

    }

    @Test
    public void testFiles() throws IOException {
        long usableSpace = Files.getFileStore(Paths.get(".")).getUsableSpace();
        System.out.println(usableSpace);
        FileStore fs = Files.getFileStore(Paths.get(basePath));
        System.out.println(fs.name() +" -> "+fs.type()+" -> "+fs.getTotalSpace());

        FileSystem fileSystem = FileSystems.getDefault();
        fileSystem.getFileStores().forEach(System.out::println);
    }

    @Test
    public void testAttributes() throws IOException {

        FileSystem fileSystem = FileSystems.getDefault();
        fileSystem.supportedFileAttributeViews().forEach(System.out::println);

        DosFileAttributes bas = Files.readAttributes(Paths.get(basePath), DosFileAttributes.class);

        System.out.println(bas.creationTime()+" : "+bas.lastAccessTime()+" : "
                        +bas.lastModifiedTime() + " : " + bas.fileKey() + " : "
                        + bas.isDirectory() + " : " + bas.isOther() + " : "+
                        bas.isRegularFile()+" : "+bas.isSymbolicLink()+" : "+bas.size()
        );

        System.out.println(bas.isArchive()+" : "+ bas.isHidden() + " : " + bas.isReadOnly() + " : " + bas.isSystem());

        Files.readAttributes(Paths.get(basePath), String.valueOf(FileOwnerAttributeView.class));
    }

    @Test
    public void testFile3() throws IOException {

        //Path path = Paths.get(newFilePath);

        //boolean notExists = Files.notExists(path);
        //System.out.println(notExists+" : "+Files.exists(path)+" : "+Files.isExecutable(path));
        //
        //System.out.println(Files.isReadable(path)+" : "+Files.isWritable(path));

        //Path file1 = Files.createFile(path);

        //Path tempPath = Paths.get(tempDirPath);
        //
        //System.out.println(Files.exists(path));

        //Path tempDirectory = Files.createTempDirectory("bak");
        //Path tempFilePath = Files.createTempFile(tempPath, "hello-",  "text");
        //BufferedWriter br = Files.newBufferedWriter(tempFilePath,
        //        Charset.defaultCharset(),
        //        StandardOpenOption.WRITE);

        //Path basePath = Paths.get(TestNIO2File.basePath);

        //Files.copy(Paths.get(basePath),Paths.get(newFilePath));

        Files.readAllLines(Paths.get(basePath), StandardCharsets.UTF_8).forEach(System.out::println);

    }

    @Test
    public void testFile4() throws IOException, URISyntaxException {

        String url = "https://www.baidu.com/s?wd=java%E5%90%A7&rsv_spt=1&rsv_iqid=0x9a26b61f00000b03&issp=1&f=3&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=7&rsv_sug1=2&rsv_sug7=100&rsv_sug2=0&prefixsug=javaba&rsp=0&inputT=2698&rsv_sug4=3959";

        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader((new URL(url)).openStream()));
        String s = "";
        while ((s = br.readLine()) != null){
            list.add(s);
        }

        Files.write(Paths.get(newTempFilePath),list,StandardCharsets.UTF_8,StandardOpenOption.CREATE_NEW);

    }

    @Test
    public void testDir() throws IOException {

        //Path directory = Files.createDirectory(Paths.get(baseDosDir+"\\tempDir"));
        //Files.deleteIfExists(Paths.get(baseDosDir+"\\tempDir"));

        Path path = Files.createTempDirectory(Paths.get(baseDosDir),null);

        SleepUtils.sleep2Second();

        //File file = path.toFile();
        //file.deleteOnExit();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    @Test
    public void testDirStream() throws IOException {

        Path path = Paths.get("C:\\Program Files\\Java");
        Files.newDirectoryStream(path).forEach(System.out::println);
        Files.newDirectoryStream(path,(p)->p.getFileName().endsWith("java"));
    }

    @Test
    public void testSymbolLink() throws IOException {

        //Files.createSymbolicLink(Paths.get(baseDosDir), Paths.get(basePath));
        Path hardLink = Files.createLink(Paths.get( baseDirPath+ "\\temp1"), Paths.get(basePath));

        System.out.println(Files.isSymbolicLink(hardLink));

        Files.readAllLines(hardLink).forEach(System.out::println);
    }

    @Test
    public void testWalkFileTree() throws IOException {
        Path path = Paths.get("C:\\code\\c");

        //Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class),Integer.MAX_VALUE,new DoNothingVisitor());

        //delete directory and file multi
        Files.walkFileTree(path,new DeleteVisitor());
    }

    @Test
    public void testDeleteDir() throws IOException {
        //Path path = Paths.get("C:\\code\\c");
        //Files.deleteIfExists(path);

        Files.delete(Paths.get("C:\\code\\c\\d"));


    }
    @Test
    public void testCopyFile() throws IOException {

        // occur errors
        Path destPath = Paths.get("C:\\code\\c");
        Path srcPath = Paths.get("C:\\code");

        System.out.println(destPath.relativize(srcPath));
        System.out.println(destPath.resolve(destPath.relativize(srcPath)));

        Files.walkFileTree(srcPath,new MoveVisitor(srcPath,destPath));
    }

    @Test
    public void testFindFilter() throws IOException {

        Path filePath = Paths.get("F:\\ideaCode\\demo1");
        String endStr = ".txt";
        //BiPredicate<Path,BasicFileAttributes> predicate = (path,attrs)->attrs.isRegularFile() && path.getFileName().endsWith(endStr);
        //
        //Stream<Path> stream = Files.find(filePath, 2, predicate);
        //stream.map(Path::getFileName).forEach(StaticUtils.println);

        Files.find(filePath, 8, (path, attrs) -> path.getFileName()
                .toString().endsWith(endStr)).forEach(StaticUtils.println);

    }

    public static void deleteFile(String path){

        //if(!file.isDirectory()) file.delete();
        //else {
        //    for(File file1 : file.listFiles()) deleteFile(file1);
        //}

        //delete file and directory ...
        File file = new File(path);
        if(file.isDirectory()){
            String[] list = file.list();
            assert list != null;
            for (String aList : list) {
                deleteFile(path + "\\" + aList);
            }
        }
        file.delete();

    }
    @Test
    public void testMather() {

        //boolean matches = FileSystems.getDefault()
        //        .getPathMatcher("glob:*.{java,txt}")
        //        .matches(Paths.get(basePath));
        //System.out.println(matches);

        //Path path = Paths.get("C:\\code\\d");

        String path = "C:\\code\\d";
        deleteFile(path);


    }
    @Test
    public void testFile() throws IOException {
        String filePath = "F:\\ideaCode";
        Files.walk(Paths.get(filePath))
                .map(Path::toFile)
                .filter(file -> file.getName().endsWith(".java"))
                .forEach(e-> System.out.println(e.getName()));
    }



    @Test
    public void testAsyncFileChannel() throws IOException, ExecutionException, InterruptedException {

        AsynchronousFileChannel afc = AsynchronousFileChannel.open(Paths.get(basePath));
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        Future<Integer> read = afc.read(buffer, 0);

        while (!read.isDone()){
            System.out.print(".");
        }
        System.out.println("finished: "+read.isDone());
        System.out.println(read.get());

    }

    @Test
    public void testAsyncFileChannel2() throws IOException {

        AsynchronousFileChannel afc = AsynchronousFileChannel.open(Paths.get(basePath));

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        Thread mainThread = Thread.currentThread();

        afc.read(buffer, 0, null, new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("Bytes read: "+result);
                mainThread.interrupt();
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("failing ... "+exc.toString());
                mainThread.interrupt();
            }
        });

        System.out.println("waiting for completing ... ");

        try {
            mainThread.join();
        } catch (InterruptedException e) {
            System.out.println("terminating ... ");
        }

        afc.close();

    }

    @Test
    public void testAsyncSocketChanne() throws IOException {

        AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open()
                .bind(new InetSocketAddress(StaticUtils.PORT));
        System.out.println("server listening at: "+assc.getLocalAddress());

        Attachment att = new Attachment();
        att.channelServer = assc;

        Thread mthread = Thread.currentThread();

        assc.accept(att, new CompletionHandler<AsynchronousSocketChannel, Attachment>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Attachment attachment) {
                try {
                    System.out.println("complete : "+result.getRemoteAddress());
                    mthread.interrupt();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Attachment attachment) {
                System.out.println("failing ,... "+exc.toString());
                mthread.interrupt();
            }
        });

        try {
            mthread.join();
        } catch (InterruptedException e) {
            System.out.println("terminating ... ");
        }
    }
    static class MoveVisitor extends SimpleFileVisitor<Path>{
        private Path srcPath;
        private Path destPath;

        public MoveVisitor(Path srcPath, Path destPath) {
            this.srcPath = srcPath;
            this.destPath = destPath;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            Path targetPath = destPath.resolve(srcPath.relativize(dir));
            Files.copy(dir,targetPath,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.COPY_ATTRIBUTES);

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Path targetPath = destPath.resolve(srcPath.relativize(file));
            Files.move(file,targetPath,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.ATOMIC_MOVE);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            if(exc == null){
                Files.delete(dir);
            }else throw exc;
            return FileVisitResult.CONTINUE;
        }
    }
    static class DeleteVisitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            if(exc == null){
                if(Files.deleteIfExists(dir))
                    System.out.println("delete directory : "+dir);
                else System.out.println("can't delete directory: "+dir);
            }else throw exc;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(Files.deleteIfExists(file))
                System.out.println("delete file: "+file.getFileName());
            else System.out.println("can't delete file: "+file.getFileName());
            return FileVisitResult.CONTINUE;
        }
    }
    static class CopyVisitor extends SimpleFileVisitor<Path>{
        private Path fromPath;
        private Path toPath;
        private StandardCopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;

        public CopyVisitor(Path fromPath, Path toPath) {
            this.fromPath = fromPath;
            this.toPath = toPath;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            System.out.println("dir = " + dir);
            System.out.println("fromPath = " + fromPath);
            System.out.println("toPath = " + toPath);
            System.out.println("fromPath.relativize(dir) = " +
                    fromPath.relativize(dir));
            System.out.println("toPath.resolve(fromPath.relativize(dir)) = " +
                    toPath.resolve(fromPath.relativize(dir)));
            Path targetPath = toPath.resolve(fromPath.relativize(dir));
            if (!Files.exists(targetPath))
                Files.createDirectory(targetPath);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("file = " + file);
            System.out.println("fromPath = " + fromPath);
            System.out.println("toPath = " + toPath);
            System.out.println("fromPath.relativize(file) = " +
                    fromPath.relativize(file));
            System.out.println("toPath.resolve(fromPath.relativize(file)) = " +
                    toPath.resolve(fromPath.relativize(file)));
            Files.copy(file, toPath.resolve(fromPath.relativize(file)),
                    copyOption);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.err.println(exc);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return super.postVisitDirectory(dir, exc);
        }
    }
    static class DoNothingVisitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

            System.out.printf("preVisitDirectory: %s%n", dir);
            System.out.printf(" lastModifiedTime: %s%n", attrs.lastModifiedTime());
            System.out.printf(" size: %d%n%n", attrs.size());

            return super.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.printf("v-visitFile: %s%n%n", file);
            System.out.printf("v-lastModifiedTime: %s%n",
                    attrs.lastModifiedTime());
            System.out.printf("v-size: %d%n%n", attrs.size());
            return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.printf("visitFileFailed: %s %s%n%n", file, exc);
            return super.visitFileFailed(file, exc);
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.printf("postVisitDirectory: %s %s%n%n", dir, exc);
            return super.postVisitDirectory(dir, exc);
        }
    }
}
