package com.bgw.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * desc：
 *
 * @author wangzhb 2019/8/8 14:39
 */
public class OutOfMemoryErrorTestCase {
    static String base = "string";

    public static void main(String[] args) {
        // byte[] bs = new byte[100 * 1024 * 1024];

        oomMetaspace();
    }

    private static void oomJavaHeapSpace() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

    private static void oomMetaspace() {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.bgw.jvm.OutOfMemoryErrorTestCase");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
