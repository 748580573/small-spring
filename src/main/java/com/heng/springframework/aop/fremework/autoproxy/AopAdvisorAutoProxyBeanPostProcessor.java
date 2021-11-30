package com.heng.springframework.aop.fremework.autoproxy;

import com.heng.springframework.aop.*;
import com.heng.springframework.aop.fremework.ProxyFactory;
import com.heng.springframework.beans.factory.*;
import com.heng.springframework.beans.factory.config.BeanPostProcessor;
import org.aspectj.lang.annotation.Aspect;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AopAdvisorAutoProxyBeanPostProcessor implements InitializingBean,BeanPostProcessor, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    private final List<AopObject> pointcutAdvisors = new ArrayList<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Object result = bean;
        for (AopObject aopObject : pointcutAdvisors){
            Pointcut pointcut = aopObject.getPointcut();
            if (pointcut.getClassFilter().matches(bean.getClass())){
                AdvisedSupport advisedSupport = new AdvisedSupport();
                TargetSource targetSource = new TargetSource(result);
                advisedSupport.setTargetSource(targetSource);
                advisedSupport.setAopObject(aopObject);
                result =  new ProxyFactory(advisedSupport).getProxy();
            }
        }
        return result;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    private void registerPointcutAdvisors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, Object> aspectMap = beanFactory.getBeansOfAnnotation(Aspect.class);
        for (Object bean : aspectMap.values()){
            AspectAopObject aopObject = new AspectAopObject(bean);
            this.addPointcutAdvisor(aopObject);
        }
    }

    private void addPointcutAdvisor(AspectAopObject aopObject){
        this.pointcutAdvisors.remove(aopObject);
        this.pointcutAdvisors.add(aopObject);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        registerPointcutAdvisors(this.beanFactory);
    }
}
