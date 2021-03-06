package com.heng.spring.test.service;

import com.heng.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "hw");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
