package com.heng.springframework.beans.factory.support;

import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.DisposableBean;
import com.heng.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    /**
     * 表示null对象。用于去除map中value为null的二义性。
     */
    protected static final Object NULL_OBJECT = new Object();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    public void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName,singletonObject);
    }

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeanMap.put(beanName,bean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeanMap.keySet();

        for (String beanName :keySet){
            DisposableBean bean = disposableBeanMap.remove(beanName);
            try {
                bean.destory();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
