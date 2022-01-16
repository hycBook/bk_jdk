package per.hyc.designPattern.Bridge;

import org.springframework.util.Assert;

import java.util.Properties;

public class TestBridge {
    public static void main(String[] args) {
        test();
        System.out.println();

        test1();
    }

    private static void test1() {
        Properties pro = GetProperties.getPro(".\\src\\main\\resources\\Bridge.properties");
        try {
            Color color = (Color) Class.forName(pro.getProperty("color")).newInstance();
            Pen pen = (Pen) Class.forName(pro.getProperty("pen")).newInstance();
            pen.setColor(color);
            pen.draw("鲜花");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        Color color = (Color) GetProperties.getBean("color");
        Pen pen = (Pen) GetProperties.getBean("pen");
        Assert.notNull(pen, "pen 不能为空");
        pen.setColor(color);
        pen.draw("鲜花");
    }
}
