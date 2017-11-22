package com.ale.file;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class NIOTest {

    @Test
    public void test01() throws IOException {
        File file = new File("F:/java/eclipseworkspace/my-project/mybatis-demo/mybatis-mapper/src/main/java/com/koko/com.koko.util/FilePathTest.java");
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(1024 * 1024);
        fc.read(bb);
        bb.flip();
        while (bb.hasRemaining()) {
            System.out.print((char) bb.get());
        }
        bb.clear();
        fc.close();
    }

    @Test
    public void test02() throws IOException {
        File file = new File("F:/java/eclipseworkspace/my-project/mybatis-demo/mybatis-mapper/src/main/java/com/koko/com.koko.util/FilePathTest.java");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fc = raf.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(10);
        String str = "abc";
        bb.put(str.getBytes());
        bb.flip();
        fc.write(bb);
        bb.clear();
        fc.close();
    }

    @Test
    public void testReadNIO() {
        String pathname = "F:/java/eclipseworkspace/my-project/mybatis-demo/mybatis-mapper/src/main/java/com/koko/com.koko.util/FilePathTest.java";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(pathname));
            FileChannel channel = fis.getChannel();

            int capacity = 1024 * 1024;// 字节
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity()
                    + "位置是：" + bf.position());
            int length = -1;

            while ((length = channel.read(bf)) != -1) {

                /*
                 * 注意，读取后，将位置置为0，将limit置为容量, 以备下次读入到字节缓冲中，从0开始存储
                 */
                bf.clear();
                byte[] bytes = bf.array();
                System.out.write(bytes, 0, length);
                System.out.println();

                System.out.println("限制是：" + bf.limit() + "容量是：" + bf.capacity()
                        + "位置是：" + bf.position());

            }

            channel.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testWriteNIO() {
        String filename = "F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-mapper\\src\\main\\java\\com\\koko\\out.txt";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filename));
            FileChannel channel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode("Hello world");
            // 字节缓冲的容量和limit会随着数据长度变化，不是固定不变的
            System.out.println("初始化容量和limit：" + src.capacity() + "," + src.limit());
            int length = 0;
            while ((length = channel.write(src)) != 0) {
                /*
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读
                 */
                System.out.println("写入长度:" + length);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testReadAndWriteNIO() {
        String pathname = "F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-mapper\\src\\main\\java\\com\\koko\\out.txt";
        FileInputStream fin = null;

        String filename = "file-out.txt";

        FileOutputStream fos = null;
        try {
            fin = new FileInputStream(new File(pathname));
            FileChannel channel = fin.getChannel();

            int capacity = 100;// 字节
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            System.out.println("限制是：" + bf.limit() + " 容量是：" + bf.capacity() + " 位置是：" + bf.position());


            fos = new FileOutputStream(new File(filename));
            FileChannel outchannel = fos.getChannel();

            int length = -1;
            while ((length = channel.read(bf)) != -1) {

                //将当前位置置为limit，然后设置当前位置为0，也就是从0到limit这块，都写入到通道中
                bf.flip();

                int outlength = 0;
                while ((outlength = outchannel.write(bf)) != 0) {
                    System.out.println("读，" + length + " 写," + outlength);
                }

                //将当前位置置为0，然后设置limit为容量，也就是从0到limit（容量）这块，
                //都可以利用，通道读取的数据存储到
                //0到limit这块
                bf.clear();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testLineNumberReader() throws FileNotFoundException {
        String filename = "F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-mapper\\file-out.txt";
        LineNumberReader lnr = new LineNumberReader(new BufferedReader(new FileReader(new File(filename))));
        System.out.println(lnr.getLineNumber());
    }

    @Test
    public void test04() throws IOException {
        String filename = "F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-mapper\\src\\main\\java\\com\\koko\\com.koko.util\\FilePathTest.java";
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        System.out.println(br.readLine());
        System.out.println(System.getProperty("user.dir"));
    }


    @Test
    public void testBufferInputStream() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-mapper\\src\\main\\java\\com\\koko\\com.koko.util\\AppendFileTest.java")));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

}
