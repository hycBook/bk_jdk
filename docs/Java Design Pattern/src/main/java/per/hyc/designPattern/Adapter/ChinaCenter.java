package per.hyc.designPattern.Adapter;

/**
 * 中国中锋
 */
public class ChinaCenter {
    private String name;

    public ChinaCenter(String name) {
        this.name = name;
    }

    public void jingong() {
        System.out.println("中锋 " + name + " 进攻");
    }

    public void fangshou() {
        System.out.println("中锋 " + name + " 防守");
    }
}