package com.heng.springframework.core.io;

import com.heng.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource {

    private final File file;

    private final String path;

    public FileSystemResource(String path){
        this.file = new File(path);
        this.path = path;
    }

    public FileSystemResource(File file){
        this.file = file;
        this.path = file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    public String getPath() {
        return path;
    }
}
