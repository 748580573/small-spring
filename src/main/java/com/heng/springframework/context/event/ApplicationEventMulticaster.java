package com.heng.springframework.context.event;

import com.heng.springframework.context.ApplicationEvent;
import com.heng.springframework.context.ApplicationListener;

public interface ApplicationEventMulticaster {

    /**
     * 添加时间监听器
     * @param applicationListener
     */
    void addApplicationListener(ApplicationListener applicationListener);

    /**
     * 移除时间监听器
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 广播事件
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
