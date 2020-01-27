package per.hyc.designPattern.Observer;

public class TestObserver {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println();

        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
