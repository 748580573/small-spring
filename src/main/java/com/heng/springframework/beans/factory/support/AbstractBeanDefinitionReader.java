package com.heng.springframework.beans.factory.support;

import com.heng.springframework.core.io.DefaultResourceLoader;
import com.heng.springframework.core.io.ResourceLoader;

/**
 * 这里体现了，在类的继承结构上的责任分工，
 * AbstractBeanDefinitionReader虽然实现了BeanDefinitionReader接口，
 * 但是AbstractBeanDefinitionReader只负责registry和resourceLoader的初始化，
 * 而BeanDefinition的加载就交给AbstractBeanDefinitionReader继承类来实现。
 * 这样做也是为了增加代码的结构的伸缩性和扩展性。
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry,ResourceLoader resourceLoader){
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
