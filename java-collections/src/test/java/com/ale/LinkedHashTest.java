package com.ale;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashTest {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("1", "安琪拉");
        map.put("2", "的");
        map.put("3", "博客");
        for (Map.Entry<String, String> item : map.entrySet()) {
            System.out.println(item.getKey() + ":" + item.getValue());
        }
    }//console输出1:安琪拉2:的3:博客
}
