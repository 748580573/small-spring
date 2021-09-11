package com.heng.springframework.context;

import java.util.EventListener;

public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    /**
     * 事件发生时，该方法被回调
     * @param event
     */
    void onApplicationEvent(E event);
}
