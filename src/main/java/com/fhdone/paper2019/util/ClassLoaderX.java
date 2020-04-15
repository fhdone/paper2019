package com.fhdone.paper2019.util;

import java.io.FileInputStream;
import java.lang.reflect.Method;

//https://blog.csdn.net/huachao1001/article/details/52297075
public class ClassLoaderX {

    public void hello() {
        System.out.println("恩，是的，我是由 " + getClass().getClassLoader().getClass()
                + " 加载进来的");
    }

    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

    };

    public static void main(String args[]) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("/Users/fhdone/Documents/code/workspace/paper2019/src/main/java");
        Class clazz = classLoader.loadClass("com.fhdone.paper2019.util.ClassLoaderX");
        Object obj = clazz.newInstance();

        Method helloMethod = clazz.getDeclaredMethod("hello", null);
        helloMethod.invoke(obj, null);
    }

}
