package com.heng.springframework.beans.factory.support;

import com.heng.springframework.aop.AopObject;
import com.heng.springframework.aop.PointcutAdvisor;
import com.heng.springframework.beans.factory.BeanFactory;
import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.heng.springframework.beans.factory.FactoryBean;
import com.heng.springframework.beans.factory.config.BeanDefinition;
import com.heng.springframework.beans.factory.config.BeanPostProcessor;
import com.heng.springframework.util.ClassUtils;
import com.heng.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableListableBeanFactory {


    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();


    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    private final List<AopObject> pointcutAdvisors = new ArrayList<>();


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
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    protected abstract BeanDefinition getBeandefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition,Object[] args) throws  BeansException;

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addPointcutAdvisor(AopObject aopObject){
        pointcutAdvisors.remove(aopObject);
        pointcutAdvisors.add(aopObject);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    public List<BeanPostProcessor> getBeanPostProcessors(){
        return this.beanPostProcessors;
    }

    public List<AopObject>  getPointcutAdvisors(){
        return this.pointcutAdvisors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
