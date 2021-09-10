package com.heng.springframework.context;

import com.heng.springframework.beans.factory.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     * @throws BeansException
     */
    void refresh() throws BeansException;

    /**
     * 调用实现了Dis
     */
    void close();

    /**
     * 向虚拟机注册hook
     */
    void registerShutdownHook();
}
