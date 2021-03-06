package com.heng.springframework.core.io;

import cn.hutool.core.lang.Assert;
import com.heng.springframework.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path){
        this(path,null);
    }

    public ClassPathResource(String path,ClassLoader classLoader){
        Assert.notNull(path,"Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream input = classLoader.getResourceAsStream(path);
        if (input == null){
            throw new FileNotFoundException(
                    this.path + " cannot be opened because it does not exist");
        }
        return input;
    }
}
