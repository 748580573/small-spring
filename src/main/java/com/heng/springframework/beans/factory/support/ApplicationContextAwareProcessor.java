package com.heng.springframework.beans.factory.support;

import com.heng.springframework.beans.factory.ApplicationContextAware;
import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.config.BeanPostProcessor;
import com.heng.springframework.context.ApplicationContext;

import java.util.Map;

public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware)bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
