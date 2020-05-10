package com.ale.io;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PathTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathTest.class);

    public static void main(String[] args) {

        Path path = FileSystems.getDefault().getPath("/src/main/java/com/koko/com.koko.util/PathTest.java");

        System.out.println("toString: " + path.toString());

        System.out.printf("getFileName: %s\n", path.getFileName());
        System.out.printf("getRoot: %s\n", path.getRoot());
        System.out.printf("getNameCount: %d\n", path.getNameCount());

        for (int index = 0; index < path.getNameCount(); index++) {
            System.out.printf("getName(%d): %s\n", index, path.getName(index));
        }

        System.out.printf("subpath(0,2): %s\n", path.subpath(0, 2));
        System.out.printf("getParent: %s\n", path.getParent());
        System.out.println(path.isAbsolute());

        try {
            path = Paths.get("com", "koko", "com/koko/test/util", "PathTest.java");
            System.out.printf("Absolute path: %s", path.toAbsolutePath());
        } catch (InvalidPathException ex) {
            System.out.printf("Bad path: [%s] at position %s", ex.getInput(), ex.getIndex());
        }

    }


    @Test
    public void test01() {
        String separator = FileSystems.getDefault().getSeparator();
        System.out.println("The separator is " + separator);
        try {
            Path path = Paths.get(new URI("file:///F:/java/eclipseworkspace/my-project/mybatis-demo/mybatis-mapper/src/main/java/com/koko/com.koko.util/PathTest.java"));
            System.out.println("subpath: " + path.subpath(0, 3));

            System.out.println("File exists: " + Files.exists(path));
        } catch (URISyntaxException ex) {
            System.out.println("Bad URI");
        } catch (InvalidPathException ex) {
            System.out.println("Bad path: [" + ex.getInput() + "] at position            " + ex.getIndex());
        }
    }

    @Test
    public void test02() {
        Path path1 = null;
        Path path2 = null;

        path1 = Paths.get("/programing/./liunx+docker+tomcat部署web项目.txt");
        path2 = Paths.get("/programing/./服务器.txt");

        System.out.println(Files.isSymbolicLink(path1));
        System.out.println(Files.isSymbolicLink(path2));

        try {

            Path path = Paths.get("G:/programing/./服务器.txt");

            System.out.println("Normalized: " + path.normalize());
            System.out.println("Absolute path: " + path.toAbsolutePath());
            System.out.println("URI: " + path.toUri());
            System.out.println("toRealPath (Do not follow links): " + path.toRealPath(LinkOption.NOFOLLOW_LINKS));
            System.out.println("toRealPath: " + path.toRealPath());

            Path firstPath = Paths.get("/home/music/users.txt");
            Path secondPath = Paths.get("/docs/status.txt");

            System.out.println("From firstPath to secondPath: " + firstPath.relativize(secondPath));
            System.out.println("From secondPath to firstPath: " + secondPath.relativize(firstPath));
            System.out.println("exists (Do not follow links): " + Files.exists(firstPath, LinkOption.NOFOLLOW_LINKS));
            System.out.println("exists: " + Files.exists(firstPath));
            System.out.println("notExists (Do not follow links): " + Files.notExists(firstPath, LinkOption.NOFOLLOW_LINKS));
            System.out.println("notExists: " + Files.notExists(firstPath));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test03() throws IOException {
        //String pathText = "G:/programing/服务器.txt";
        String pathText1 = "D:/home/projects/java.exe";
        Path path = Paths.get(pathText1);

        // Files类的probeContentType就是用来获得文件内容类型的。
        String type = Files.probeContentType(path);
        System.out.println(type);
    }

    @Test
    public void test04() {
        //Path path = Paths.get("G:/programing/服务器.txt");
        Path path = Paths.get("G:/", "programing", "服务器.txt");
        try {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            System.out.println("Creation Time: " + attributes.creationTime());
            System.out.println("Last Accessed Time: " + attributes.lastAccessTime());
            System.out.println("Last Modified Time: " + attributes.lastModifiedTime());
            System.out.println("File Key: " + attributes.fileKey());
            System.out.println("Directory: " + attributes.isDirectory());
            System.out.println("Other Type of File: " + attributes.isOther());
            System.out.println("Regular File: " + attributes.isRegularFile());
            System.out.println("Symbolic File: " + attributes.isSymbolicLink());
            System.out.println("Size: " + attributes.size());

            List<String> lines = Files.readAllLines(path, Charset.forName("utf-8"));
            for (String line : lines) {
                System.out.println(line);
            }

        } catch (IOException exception) {
            System.out.println("Attribute error");
        }
    }

    @Test
    public void test05() throws IOException {
        Path path = Paths.get("G:/", "programing", "服务器.txt");
        List<String> lines = Files.readAllLines(path,Charset.forName("GBK"));
        for (String line : lines) {
            System.out.println(line);
        }
        String content = "ok";
        Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        System.out.println("--->ok");

    }

    @Test
    public void test06(){
        Path path = Paths.get("F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-file\\src\\main\\java\\com\\koko\\file\\util\\file\\AppendFileTest.java");
        try {
            Map<String, Object> attrsMap = Files.readAttributes(path, "*");
            Set<String> keys = attrsMap.keySet();
            for (String attribute : keys) {
                System.out.println(attribute + ": " + Files.getAttribute(path, attribute));
            }
        } catch (IOException exception) {
            System.out.println("IOException");
        }
    }
}

