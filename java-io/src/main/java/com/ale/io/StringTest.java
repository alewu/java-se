package com.ale.io;

import java.io.IOException;
import java.nio.channels.Selector;

public class StringTest {
    public static void main(String[] args) {
        System.out.println(String.valueOf(true) == "true");
        try {
            Selector selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
