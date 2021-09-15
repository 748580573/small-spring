package com.heng.springframework.context.support;

import cn.hutool.core.util.StrUtil;
import com.heng.springframework.aop.fremework.autoproxy.AopAdvisorAutoProxyBeanFactorPostProcessor;
import com.heng.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.heng.springframework.beans.factory.config.BeanDefinition;
import com.heng.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 该类负责初始化beanFactory以及刷新beanFactory
 *
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private ConfigurableListableBeanFactory beanFactory;

    /**
     * 刷新beanFactory，创建beanFactory并且加载beaenDefinition
     */
    @Override
    protected void refreshBeanFactory() {
        ConfigurableListableBeanFactory beanFactory = createBeanFactory();
        addAopProxyDefinition(beanFactory);
        loadBeanDefinition(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * loadBeanDefinition
     * @param beanFactory
     */
    protected abstract void loadBeanDefinition(ConfigurableListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }


    protected ConfigurableListableBeanFactory getBeanFactory(){
        return this.beanFactory;
    }

    private void addAopProxyDefinition(ConfigurableListableBeanFactory beanFactory){
        BeanDefinition beanDefinition = new BeanDefinition(AopAdvisorAutoProxyBeanFactorPostProcessor.class);
        beanFactory.registerBeanDefinition(StrUtil.lowerFirst(AopAdvisorAutoProxyBeanFactorPostProcessor.class.getSimpleName()),beanDefinition);

    }
}
