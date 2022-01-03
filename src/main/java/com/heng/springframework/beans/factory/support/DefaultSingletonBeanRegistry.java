package com.heng.springframework.beans.factory.support;

import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.DisposableBean;
import com.heng.springframework.beans.factory.ObjectFactory;
import com.heng.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    /**
     * 表示null对象。用于去除map中value为null的二义性。
     */
    protected static final Object NULL_OBJECT = new Object();


    // 二级缓存，提前暴漏对象，没有完全实例化的对象

    protected final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>();

    // 三级缓存，存放代理对象

    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();

    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singleBean = singletonObjects.get(beanName);
        if (null == singleBean){
            singleBean = earlySingletonObjects.get(beanName);

            if (singleBean == null){
                ObjectFactory<?> singleBeanFactory = singletonFactories.get(beanName);
                singleBean = singleBeanFactory.getObject();
                earlySingletonObjects.put(beanName,singleBean);
                singletonFactories.remove(beanName);
            }
        }
        return singletonObjects.get(beanName);
    }

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeanMap.put(beanName,bean);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
        earlySingletonObjects.remove(beanName,singletonObject);
        singletonFactories.remove(beanName,singletonObject);
    }

    protected void addSingletonFactory(String beanName,ObjectFactory<?> singletonFactory){
        /**
         * 排除掉已经创建好的bean
         */
        if (!this.singletonObjects.containsKey(beanName)){
            this.singletonFactories.put(beanName,singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
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
