package com.heng.springframework.context;

public interface ApplicationEventPublisher {

    /**
     * 发布发布
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
