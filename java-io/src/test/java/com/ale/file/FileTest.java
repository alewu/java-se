package com.ale.file;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileTest {
    @Test
    public void test01() {
        String userDir = System.getProperty("user.dir");

        String mavenPath = "src" + File.separator + "main" + File.separator + "java";

        String projectPath = "com" + File.separator + "koko";

        Path path = Paths.get(userDir, "mybatis-mapper", mavenPath, projectPath, "file.txt");

//        System.out.println(path.getFileName());
//        System.out.println(path.getRoot());
//        System.out.println(path.toUri());
//        System.out.println(path.getNameCount());

        int nameCount = path.getNameCount();
        path.subpath(0, nameCount - 2);
        System.out.println(path);
        System.out.println(path.subpath(0, nameCount - 2));

        System.out.println(FileTest.class.getResource("").getPath().replace("target/classes", mavenPath));

    }

    @Test
    public void test02() {
        File file = new File("F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-mapper\\src\\main\\java\\com\\koko\\com.koko.util");
        traversal(file);
    }

    public void traversal(File root) {
        File[] list = root.listFiles();
        int listLength = list.length;
        for (int i = 0; i < listLength; i++) {
            System.out.println(list[i].getAbsolutePath());
            if (list[i].isDirectory()) {
                traversal(list[i]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-file\\src\\main\\java\\com\\koko");
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("目录名： " + dir);
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.getFileName().toString().endsWith(".java")) {
                    System.out.println("文件名： " + file.getFileName());
                }
                return CONTINUE;
            }


        });
    }

    @Test
    public void test04() throws IOException {
        final Path source = Paths.get("F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-file\\src\\main\\java\\com\\koko\\file\\util\\file");
        final Path target = Paths.get("F:\\java\\eclipseworkspace\\my-project\\mybatis-demo\\mybatis-file\\src\\main\\java\\com\\koko\\file\\util\\test1");

        Files.walkFileTree(source, EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE,
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                            throws IOException {
                        Path targetdir = target.resolve(source.relativize(dir));
                        try {
                            Files.copy(dir, targetdir);
                        } catch (FileAlreadyExistsException e) {
                            if (!Files.isDirectory(targetdir))
                                throw e;
                        }
                        return CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                            throws IOException {
                        Files.copy(file, target.resolve(source.relativize(file)));
                        return CONTINUE;
                    }
                });
    }

    @Test
    public void test09() throws IOException {
        String mavenPath = File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        //Path path = Paths.get("file:/F:/java/eclipseworkspace/my-project/mybatis-demo/mybatis-file" + "/src/main/java/" + "com/koko/file/util/file/a.txt");
        String s = "F:/java/eclipseworkspace/my-project/mybatis-demo/mybatis-file/src/main/java/com/koko/file/util/file/c.txt";
        String[] subPaths = s.split("/");
        int length = subPaths.length;
        String[] aa = new String[length - 1];
        System.arraycopy(subPaths, 1, aa, 0, length - 1);
        Path path = Paths.get("F:", aa);

        System.out.println(path);
        Files.createFile(path);
    }

}
