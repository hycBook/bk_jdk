package per.hyc.ReflexAndInvokeTest;


interface Fruit {
    void eat();

    void wash();

    boolean setPrice(Double price);
}


class Apple implements Fruit {
    @Override
    public void eat() {
        System.out.println("I like Apple");
    }

    @Override
    public void wash() {
        System.out.println("I wash Apple");
    }

    @Override
    public boolean setPrice(Double price) {
        System.out.println("set Apple price is " + price);
        return true;
    }
}


class Orange implements Fruit {
    @Override
    public void eat() {
        System.out.println("I eat Orange");
    }

    @Override
    public void wash() {
        System.out.println("I wash Orange");
    }

    @Override
    public boolean setPrice(Double price) {
        System.out.println("set Orange price is " + price);
        return true;
    }

}

class FruitFactory {
    static Fruit getInstance(String className) {
        Fruit fruit = null;
        try {
            fruit = (Fruit) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fruit;
    }
}