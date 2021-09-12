package com.heng.springframework.beans.factory.config;

import com.heng.springframework.beans.factory.BeansException;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在 Bean 对象执行初始化方法之前，执行此方法
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
