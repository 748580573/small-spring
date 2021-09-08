package com.heng.springframework.beans.factory;

import com.heng.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {

    public Object getBean(String name);

    Object getBean(String name,Object... args) throws BeansException;


}
