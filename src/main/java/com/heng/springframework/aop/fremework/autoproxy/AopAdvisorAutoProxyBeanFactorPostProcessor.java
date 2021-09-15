package com.heng.springframework.aop.fremework.autoproxy;

import cn.hutool.core.util.StrUtil;
import com.heng.springframework.beans.factory.BeansException;
import com.heng.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.heng.springframework.beans.factory.config.BeanDefinition;
import com.heng.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * 将ConfigurableListableBeanFactory进行bean注册
 */
public class AopAdvisorAutoProxyBeanFactorPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = new BeanDefinition(AopAdvisorAutoProxyBeanPostProcessor.class);
        beanFactory.registerBeanDefinition(StrUtil.lowerFirst(AopAdvisorAutoProxyBeanPostProcessor.class.getSimpleName()),beanDefinition);
    }
}
