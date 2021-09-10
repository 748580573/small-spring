package com.heng.springframework.beans.factory;

import com.heng.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.heng.springframework.beans.factory.config.BeanDefinition;
import com.heng.springframework.beans.factory.config.BeanPostProcessor;
import com.heng.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.heng.springframework.beans.factory.support.BeanDefinitionRegistry;

public interface ConfigurableListableBeanFactory extends BeanDefinitionRegistry,ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
