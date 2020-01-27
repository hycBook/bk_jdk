package per.hyc.designPattern.Bridge;

public interface Color {
    void bepaint(String penType, String name);
}

class Blue implements Color {
    @Override
    public void bepaint(String penType, String name) {
        System.out.println(penType + "蓝色的" + name + ".");
    }
}


class Green implements Color {
    @Override
    public void bepaint(String penType, String name) {
        System.out.println(penType + "绿色的" + name + ".");
    }
}

class Red implements Color {
    @Override
    public void bepaint(String penType, String name) {
        System.out.println(penType + "红色的" + name + ".");
    }
}
