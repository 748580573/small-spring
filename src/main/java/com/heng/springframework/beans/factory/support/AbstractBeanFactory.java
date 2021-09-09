package com.heng.springframework.beans.factory.support;

import com.heng.springframework.beans.factory.BeanFactory;
import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.heng.springframework.beans.factory.config.BeanDefinition;
import com.heng.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableListableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();


    @Override
    public Object getBean(String name) {
        return doGetBean(name,null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> clazz) {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(String name,Object[] args){
        Object bean = getSingleton(name);
        if (bean != null){
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeandefinition(name);
        return (T) createBean(name,beanDefinition,args);
    }

    protected abstract BeanDefinition getBeandefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws  BeansException;

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }
}
