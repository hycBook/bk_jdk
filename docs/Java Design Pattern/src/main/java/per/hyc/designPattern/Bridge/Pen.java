package per.hyc.designPattern.Bridge;

public abstract class Pen {
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void draw(String name);
}

class SmallPen extends Pen {
    @Override
    public void draw(String name) {
        String penType = "小号毛笔绘制";
        this.color.bepaint(penType, name);
    }
}

class MiddlePen extends Pen {
    @Override
    public void draw(String name) {
        String penType = "中号毛笔绘制";
        this.color.bepaint(penType, name);
    }
}

class BigPen extends Pen {
    @Override
    public void draw(String name) {
        String penType = "大号毛笔绘制";
        this.color.bepaint(penType, name);
    }
}