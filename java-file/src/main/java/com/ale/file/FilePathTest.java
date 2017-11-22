package com.ale.file;



import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;

public class FilePathTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        println("返回的是当前Class这个类所在包的路径>>>>>>" + FilePathTest.class.getResource("").getPath());
        println("得到当前类的classpath目录>>>" + FilePathTest.class.getResource("/").getPath().replaceFirst("/",""));
        println("得到当前类的classpath目录>>>" + FilePathTest.class.getClassLoader().getResource("") + "");

        println("绝对路径>>>>>>>>>>>>>>>>>>" + new File("/").getAbsolutePath());
        println("获得项目根目录的绝对路径>>>>>>>>>>>>>>>>>>" + new File("").getAbsolutePath());
        println("获得路径>>>>>>>>>>>>>>>>>>" + new File("").getCanonicalPath());
        println("获得getPath路径>>>>>>>>>>>>>>>>>>" + new File("").getPath());

        println("类路径下的文件>>>>>>>>>>>>>" + FilePathTest.class.getClassLoader().getResource("jdbc.properties"));


        println("线程: " + Thread.currentThread().getContextClassLoader().getResource(""));
        println("类加载器，系统: " + ClassLoader.getSystemResource(""));
        println(FilePathTest.class.getResource("").getPath());

        File file = new File(FilePathTest.class.getResource("").getPath() + "a.txt");

        println("获取标准路径  " + file.getCanonicalPath());

        println("获得项目根目录的绝对路径>>>>>" + System.getProperty("user.dir"));

        println("获取所有的类路径 包括jar包的路径" + System.getProperty("java.class.path"));

        println(FileSystems.getDefault().getPath("").toString());
//F:\java\eclipseworkspace\my-project\mybatis-demo\mybatis-mapper\src\main\java\com\koko\entity\Role.java

    }


    public static void println(String str) {
        System.out.println(str);
    }
}
