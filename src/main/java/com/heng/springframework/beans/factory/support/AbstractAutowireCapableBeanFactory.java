package com.heng.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.heng.springframework.PropertyValues;
import com.heng.springframework.beans.PropertyValue;
import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.heng.springframework.beans.factory.config.BeanDefinition;
import com.heng.springframework.beans.factory.config.BeanPostProcessor;
import com.heng.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这里体现了类的责任分层，AbstractAutowireCapableBeanFactory只负责了创建bean的实现，不对其他的接口的实现进行负责
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition,beanName,args);
            applyPropertyValues(beanName,bean,beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initiallizeBean(beanName,bean,beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName,bean);

        return bean;
    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue pv : propertyValues.getPropertyValues()){
            String name = pv.getName();
            Object value = pv.getValue();

            if (value instanceof BeanReference){
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getBeanName());
            }
            //属性填充
            BeanUtil.setFieldValue(bean,name,value);


        }
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors){
            if (null != args && args.length == ctor.getParameterTypes().length){
                constructor = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition,beanName,constructor,args);
    }

    private Object initiallizeBean(String beanName, Object bean, BeanDefinition beanDefinition) {

        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;

    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }

    private Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            Object current = beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            if (null == current){
                return result;
            }
            result = current;
        }
        return result;
    }

    private Object applyBeanPostProcessorsAfterInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()){
            Object current = beanPostProcessor.postProcessAfterInitialization(bean,beanName);
            if (current == null){
                return result;
            }
            result = current;
        }
        return result;
    }



    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


}
