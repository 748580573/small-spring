package com.heng.springframework.beans.factory.support;

import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FactoryBeanRegistrySupport 类主要处理的就是关于 FactoryBean 此类对象的注册操作，之所以放到这样一个单独的类里，就是希望做到不同领域模块下只负责各自需要完成的功能，避免因为扩展导致类膨胀到难以维护。
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{

    /**
     * Cache of singleton objects created by FactoryBeans: FactoryBean name --> object
     * 缓存由FactoryBean.getObject()方法创建的bean
     */

    private final Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectForFactoryBean(String beanName){
        Object object = this.factoryBeanObjectCache.get(beanName);
        return (object != NULL_OBJECT ? object : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory,String beanName) {
        Object object = this.factoryBeanObjectCache.get(beanName);
        if (object == null){
            object = doGetObjectFromFactoryBean(factory,beanName);
            this.factoryBeanObjectCache.put(beanName,(object != null ? object :NULL_OBJECT));
        }
        return (object != NULL_OBJECT ? object : null);
    }

    private Object doGetObjectFromFactoryBean(FactoryBean factoryBean,String beanName){
        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
