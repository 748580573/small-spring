package com.heng.springframework.core.io;

/**
 * 定义获取资源接口，里面传递 location 地址即可。类似于Resource的工厂，传入location然后获取对应的资源的输入流
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
