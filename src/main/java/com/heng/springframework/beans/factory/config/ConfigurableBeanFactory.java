package com.heng.springframework.beans.factory.config;

import com.heng.springframework.beans.factory.HierarchicalBeanFactory;
import com.heng.springframework.util.StringValueResolver;

public interface ConfigurableBeanFactory  extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    /**
     * 添加一个beanFactory字符串解析器
     * @param valueResolver
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * 解析一个占位符
     * @param value
     * @return
     */
    String resolveEmbeddedValue(String value);


}
