package per.hyc.ReflexAndInvokeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 获取properties文件的配置
 */
public class GetProperties {
    public static Properties getPro(String pathname) {
        Properties pro = new Properties();
        File f = new File(pathname);
        try {
            if (f.exists()) {
                pro.load(new FileInputStream(f));
            }
            //建立一个新的属性文件，同时设置好默认内容
            else {
                pro.setProperty("apple", "per.hyc.ReflexAndInvokeTest.Apple");
                pro.setProperty("orange", "per.hyc.ReflexAndInvokeTest.Orange");
                pro.store(new FileOutputStream(f), "FRUIT CLASS");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }
}