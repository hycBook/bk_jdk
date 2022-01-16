package per.hyc.designPattern.Proxy;

public interface IGiveGift {
    void giveDolls();

    void giveFlowers();
}

class GiftProxy implements IGiveGift {
    private Pursuit gg;

    public GiftProxy(SchoolGirl mm) {
        gg = new Pursuit(mm);
    }

    @Override
    public void giveDolls() {
        gg.giveDolls();
    }

    @Override
    public void giveFlowers() {
        gg.giveFlowers();
    }
}


class Pursuit implements IGiveGift {
    private SchoolGirl mm;

    public Pursuit(SchoolGirl mm) {
        this.mm = mm;
    }

    @Override
    public void giveDolls() {
        System.out.println(mm.getName() + " 送你洋娃娃");
    }

    @Override
    public void giveFlowers() {
        System.out.println(mm.getName() + " 送你鲜花");
    }
}