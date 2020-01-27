package per.hyc.designPattern.Adapter;

/**
 * 翻译者（适配器）
 */
public class Translator extends AbsPlayer {
    ChinaCenter center = null;

    public Translator(String name) {
        super(name);
        center = new ChinaCenter(name);
    }

    @Override
    public void attack() {
        center.jingong();
    }

    @Override
    public void defense() {
        center.fangshou();
    }
}