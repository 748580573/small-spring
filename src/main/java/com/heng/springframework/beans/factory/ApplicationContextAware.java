package com.heng.springframework.beans.factory;

import com.heng.springframework.context.ApplicationContext;

public interface ApplicationContextAware extends Aware{

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
