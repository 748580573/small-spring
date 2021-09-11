package com.heng.springframework.context.event;

import com.heng.springframework.beans.factory.BeanFactory;
import com.heng.springframework.context.ApplicationEvent;
import com.heng.springframework.context.ApplicationListener;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{


    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }


    /**
     * 广播事件
     * @param event
     */
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
