package com.heng.springframework.beans.factory.config;

import com.heng.springframework.PropertyValues;
import com.heng.springframework.beans.factory.ConfigurableListableBeanFactory;

public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableListableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableListableBeanFactory.SCOPE_SINGLETON;

    private String scope = SCOPE_SINGLETON;

    private Class<?> beanClass;

    private PropertyValues propertyValues;

    private String initMethodName;

    private String destoryMethodName;

    private boolean singleton = true;

    private boolean prototype = false;

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

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestoryMethodName() {
        return destoryMethodName;
    }

    public void setDestoryMethodName(String destoryMethodName) {
        this.destoryMethodName = destoryMethodName;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }

    public void setPrototype(boolean prototype) {
        this.prototype = prototype;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
}
