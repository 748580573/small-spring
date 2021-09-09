package com.heng.springframework.beans.factory.support;

import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.core.io.Resource;
import com.heng.springframework.core.io.ResourceLoader;

/**
 * BeanDefinition加载器的抽象，BeanDefinition的信息可以从classpath、URL、File中去获取
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
