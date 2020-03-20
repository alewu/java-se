package com.ale;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alewu
 * @date 2018/7/1 10:06
 */
public class HashMapTest {
    @Test
    public void whenHashCodeIsCalledOnPut_thenCorrect() {
        MyKey key = new MyKey(1);
        Map<MyKey, String> map = new HashMap<>();
        map.put(key, "val");
    }
}
