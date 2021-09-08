package com.heng.springframework.beans.factory.config;

/**
 * BeanReference用于表示一个bean的属性是引用类型
 * 如果bean的属性是引用类型，那么就能beanName去获取对应的对象
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName){
        this.beanName = beanName;
    }

    public String getBeanName(){
        return this.beanName;
    }
}
