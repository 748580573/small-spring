package com.heng.spring.test.service;

import com.heng.spring.test.service.MyEvent;
import com.heng.springframework.context.ApplicationListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("收到：" + event.getSource() + "消息;时间：" + simpleDateFormat.format(new Date()));
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
