package per.hyc.designPattern.Adapter;

/**
 * 球员
 */
public abstract class AbsPlayer {
    protected String name;

    public AbsPlayer(String name) {
        this.name = name;
    }

    public abstract void attack();

    public abstract void defense();
}

/**
 * 前锋
 */
class Forwards extends AbsPlayer {
    public Forwards(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("前锋 " + name + " 进攻");
    }

    @Override
    public void defense() {
        System.out.println("前锋 " + name + " 防守");
    }
}

/**
 * 中锋
 */
class Center extends AbsPlayer {
    public Center(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("中锋 " + name + " 进攻");
    }

    @Override
    public void defense() {
        System.out.println("中锋 " + name + " 防守");
    }
}

/**
 * 后卫
 */
class Guards extends AbsPlayer {
    public Guards(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("后卫 " + name + " 进攻");
    }

    @Override
    public void defense() {
        System.out.println("后卫 " + name + " 防守");
    }
}

