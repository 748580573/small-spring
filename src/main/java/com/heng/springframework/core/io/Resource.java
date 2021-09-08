package com.heng.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource表示各种资源，File,network,classpath用于获取资源的输入流
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
