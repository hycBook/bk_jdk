package per.hyc.designPattern.Singleton;

/**
 * 1、懒汉式，线程不安全
 * 是否 Lazy 初始化：是
 * 是否多线程安全：否
 * 实现难度：易
 * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
 */
class SingleObject_1 {
    private static SingleObject_1 instance;

    private SingleObject_1() {
    }

    public static SingleObject_1 getInstance() {
        if (instance == null) {
            instance = new SingleObject_1();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("1、懒汉式，线程不安全");
    }
}

/**
 * 2、懒汉式，线程安全
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * 实现难度：易
 * 描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
 * 优点：第一次调用才初始化，避免内存浪费。
 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 * getInstance() 的性能对应用程序不是很关键（该方法使用不太频繁）。
 */
class SingleObject_2 {
    private static SingleObject_2 instance;

    private SingleObject_2() {
    }

    public static synchronized SingleObject_2 getInstance() {
        if (instance == null) {
            instance = new SingleObject_2();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("2、懒汉式，线程安全");
    }
}

/**
 * 3、饿汉式
 * 是否 Lazy 初始化：否
 * 是否多线程安全：是
 * 实现难度：易
 * 描述：这种方式比较常用，但容易产生垃圾对象。
 * 优点：没有加锁，执行效率会提高。
 * 缺点：类加载时就初始化，浪费内存。
 * 它基于 classloader 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，
 * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法，
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance
 * 显然没有达到 lazy loading 的效果。
 */
class SingleObject_3 {
    private static SingleObject_3 instance = new SingleObject_3();

    private SingleObject_3() {
    }

    public static SingleObject_3 getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("3、饿汉式");
    }
}


/**
 * 4、双检锁/双重校验锁（DCL，即 double-checked locking）
 * JDK 版本：JDK1.5 起
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * 实现难度：较复杂
 * 描述：这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * getInstance() 的性能对应用程序很关键。
 */
class SingleObject_4 {
    private volatile static SingleObject_4 singleton;

    private SingleObject_4() {
    }

    public static SingleObject_4 getInstance() {
        if (singleton == null) {
            synchronized (SingleObject_4.class) {
                if (singleton == null) {
                    singleton = new SingleObject_4();
                }
            }
        }
        return singleton;
    }

    public void showMessage() {
        System.out.println("4、双检锁/双重校验锁（DCL，即 double-checked locking）");
    }
}

/**
 * 5、登记式/静态内部类
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 * 实现难度：一般
 * 描述：这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，
 * 应使用这种方式而不是双检锁方式。这种方式只适用于静态域的情况，
 * 双检锁方式可在实例域需要延迟初始化时使用。
 * 这种方式同样利用了 classloader 机制来保证初始化 instance 时只有一个线程，
 * 它跟第 3 种方式不同的是：第 3 种方式只要 Singleton 类被装载了，
 * 那么 instance 就会被实例化（没有达到 lazy loading 效果），
 * 而这种方式是 Singleton 类被装载了，instance 不一定被初始化。
 * 因为 SingletonHolder 类没有被主动使用，只有通过显式调用 getInstance 方法时，
 * 才会显式装载 SingletonHolder 类，从而实例化 instance。
 * 想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，
 * 另外一方面，又不希望在 Singleton 类加载时就实例化，
 * 因为不能确保 Singleton 类还可能在其他的地方被主动使用从而被加载，
 * 那么这个时候实例化 instance 显然是不合适的。
 * 这个时候，这种方式相比第 3 种方式就显得很合理。
 */
class SingleObject_5 {
    private static class SingletonHolder {
        private static final SingleObject_5 INSTANCE = new SingleObject_5();
    }

    private SingleObject_5() {
    }

    public static final SingleObject_5 getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("5、登记式/静态内部类");
    }
}

/**
 * 6、枚举
 * JDK 版本：JDK1.5 起
 * 是否 Lazy 初始化：否
 * 是否多线程安全：是
 * 实现难度：易
 * 描述：这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。
 * 它更简洁，自动支持序列化机制，绝对防止多次实例化。
 * 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，
 * 而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化。
 * 不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，
 * 在实际工作中，也很少用。
 * 不能通过 reflection attack 来调用私有构造方法。
 */
enum SingleObject_6 {
    INSTANCE;

    public void showMessage() {
        System.out.println("6、枚举");
    }
}

/**
 * 经验之谈：一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。
 * 只有在要明确实现 lazy loading 效果时，才会使用第 5 种登记方式。
 * 如果涉及到反序列化创建对象时，可以尝试使用第 6 种枚举方式。
 * 如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。
 */
public class Singleton {
    static void TestSingleObject_1() {
        //不合法的构造函数
        //编译时错误：构造函数 SingleObject() 是不可见的
        //SingleObject object = new SingleObject();

        //获取唯一可用的对象
        SingleObject_1 object = SingleObject_1.getInstance();

        //显示消息
        object.showMessage();
    }

    static void TestSingleObject_2() {
        //获取唯一可用的对象
        SingleObject_2 object = SingleObject_2.getInstance();

        //显示消息
        object.showMessage();
    }

    static void TestSingleObject_3() {
        //获取唯一可用的对象
        SingleObject_3 object = SingleObject_3.getInstance();

        //显示消息
        object.showMessage();
    }

    static void TestSingleObject_4() {
        //获取唯一可用的对象
        SingleObject_4 object = SingleObject_4.getInstance();

        //显示消息
        object.showMessage();
    }


    static void TestSingleObject_5() {
        //获取唯一可用的对象
        SingleObject_5 object = SingleObject_5.getInstance();

        //显示消息
        object.showMessage();
    }

    static void TestSingleObject_6() {
        //获取唯一可用的对象
        SingleObject_6 object = SingleObject_6.INSTANCE;

        //显示消息
        object.showMessage();
    }


    public static void main(String[] args) {
        TestSingleObject_1();
        TestSingleObject_2();
        TestSingleObject_3();
        TestSingleObject_4();
        TestSingleObject_5();
        TestSingleObject_6();

    }
}

