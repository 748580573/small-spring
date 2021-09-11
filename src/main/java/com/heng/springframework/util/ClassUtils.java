package com.heng.springframework.util;

import cn.hutool.core.util.ClassUtil;

public class ClassUtils {

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;

        try {
            cl = Thread.currentThread().getContextClassLoader();
        }catch (Throwable ex){
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }
}
