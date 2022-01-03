package com.heng.springframework.beans.factory;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
