package lapd.k.codegen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    public String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    /**
     * 如果父的类加载器中都找不到name指定的类，
     * 就会调用这个方法去从磁盘上加载一个类
     *
     * @param name 类的全限定包名 不带后缀  例如com.test.Notice  而不要写成com.test.Notice.java
     * @return
     * @throws java.io.IOException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = null;
        Class<?> clazz = null;
        try {
            //加载类的字节码
            classBytes = loadClassBytes(name);
            //将字节码交给JVM
            clazz = defineClass(name, classBytes, 0, classBytes.length);
            if (clazz == null) {
                throw new ClassNotFoundException(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * 加载类的字节码
     *
     * @param name 类的全限定包名 不带后缀  例如com.test.Notice  而不要写成com.test.Notice.java
     * @return
     * @throws java.io.IOException
     */
    private byte[] loadClassBytes(String name) throws IOException {
//        String classPackageName = name.replace(".", File.separator)+".class";
//        String classAbsolutePath = classPath+classPackageName;
//        //编译java文件
//        javac(name);
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return bytes;
    }
}
