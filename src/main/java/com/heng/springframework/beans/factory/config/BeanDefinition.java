package com.heng.springframework.beans.factory.config;

import com.heng.springframework.PropertyValues;

public class BeanDefinition {

    private Class<?> beanClass;

    private PropertyValues propertyValues;


    public BeanDefinition(Class<?> beanClass){
        this.beanClass = beanClass;
        propertyValues = new PropertyValues();
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
