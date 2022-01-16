package per.hyc.designPattern.AbstractFactory;

import per.hyc.ReflexAndInvokeTest.GetProperties;

import java.util.Date;
import java.util.Properties;

/**
 * User类
 */
class User {
    private int uid;
    private String uname;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}

interface IUser {
    public void insert(User user);

    public User getUser(int uid);
}

class MysqlUser implements IUser {
    @Override
    public void insert(User user) {
        System.out.println("在mysql中的user表中插入一条元素");
    }

    @Override
    public User getUser(int id) {
        System.out.println("在mysql中的user表得到id为" + id + "的一条数据");
        return null;
    }
}

class OracleUser implements IUser {

    @Override
    public void insert(User user) {
        System.out.println("在oracle中的user表中插入一条元素");
    }

    @Override
    public User getUser(int uid) {
        System.out.println("在oracle中的user表得到id为" + uid + "的一条数据");
        return null;
    }
}


/**
 * Login类
 */
class Login {
    private int id;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

interface ILogin {
    public void insert(Login login);

    public Login getLogin(int id);
}

class MysqlLogin implements ILogin {
    @Override
    public void insert(Login login) {
        System.out.println("对 MySQL 里的 Login 表插入了一条数据");
    }

    @Override
    public Login getLogin(int id) {
        System.out.println("通过 uid 在 MySQL 里的 Login 表得到了一条数据");
        return null;
    }
}


class OracleLogin implements ILogin {
    @Override
    public void insert(Login login) {
        System.out.println("对 Oracle 里的 Login 表插入了一条数据");
    }

    @Override
    public Login getLogin(int id) {
        System.out.println("通过 uid 在 Oracle 里的 Login 表得到了一条数据");
        return null;
    }
}

/**
 * 基础抽象工厂（普通版）
 */
interface ISqlFactory {
    IUser createUser();

    ILogin createLogin();
}

class MysqlFactory implements ISqlFactory {
    @Override
    public IUser createUser() {
        return new MysqlUser();
    }

    @Override
    public ILogin createLogin() {
        return new MysqlLogin();
    }
}

class OracleFactory implements ISqlFactory {
    @Override
    public IUser createUser() {
        return new OracleUser();
    }

    @Override
    public ILogin createLogin() {
        return new OracleLogin();
    }
}

/**
 * 抽象工厂的改进（反射+配置文件+简单工厂）
 */
class EasyFactory {
    private static Properties pro = GetProperties.getPro("src\\main\\resources\\abstractFactory.properties");

    public static IUser createUser() throws Exception {
        return (IUser) Class.forName(pro.getProperty("user")).newInstance();
    }

    public static ILogin createLogin() throws Exception {
        return (ILogin) Class.forName(pro.getProperty("login")).newInstance();
    }
}


/**
 * https://blog.csdn.net/u012156116/article/details/80857255
 */
public class AbstractFactoryE2 {
    /**
     * 抽象工厂模式的优缺点：
     * <p>
     * 优点：
     * <p>
     * 1. 抽象工厂模式最大的好处是易于交换产品系列，由于具体工厂类，例如 IFactory factory=new OracleFactory(); 
     * 在一个应用中只需要在初始化的时候出现一次，这就使得改变一个应用的具体工厂变得非常容易，它只需要改变具体工厂即可使用不同的产品配置。
     * 不管是任何人的设计都无法去完全防止需求的更改，或者项目的维护，那么我们的理想便是让改动变得最小、最容易，
     * 例如我现在要更改以上代码的数据库访问时，只需要更改具体的工厂即可。
     * <p>
     * 2. 抽象工厂模式的另一个好处就是它让具体的创建实例过程与客户端分离，客户端是通过它们的抽象接口操作实例，
     * 产品实现类的具体类名也被具体的工厂实现类分离，不会出现在客户端代码中。就像我们上面的例子，客户端只认识IUser和ILogin，
     * 至于它是MySQl里的表还是Oracle里的表就不知道了。
     * <p>
     * 缺点：
     * <p>
     * 1. 如果你的需求来自增加功能，比如增加Login表，就有点太烦了。首先需要增加 ILogin，mysqlLogin,oracleLogin。
     * 然后我们还要去修改工厂类： sqlFactory， mysqlFactory， oracleFactory 才可以实现，需要修改三个类，实在是有点麻烦。
     * <p>
     * 2. 还有就是，客户端程序肯定不止一个，每次都需要声明sqlFactory factory=new MysqlFactory()，
     * 如果有100个调用数据库的类，就需要更改100次sqlFactory factory=new oracleFactory()。
     */
    static void TestAbstractFactory() {
        User user = new User();
        Login login = new Login();

        // 只需要确定实例化哪一个数据库访问对象给factory
        // IFactory factory=new MysqlFactory();
        ISqlFactory factory = new OracleFactory();

        // 已与具体的数据库访问解除了耦合
        IUser userOperation = factory.createUser();
        userOperation.getUser(1);
        userOperation.insert(user);

        // 已与具体的数据库访问解除了耦合
        ILogin loginOperation = factory.createLogin();
        loginOperation.insert(login);
        loginOperation.getLogin(1);
    }

    /**
     * 由于事先在简单工厂类里设置好了db的值，所以简单工厂的方法都不需要由客户端来输入参数，这样在客户端就只需要使用 EasyFactory.createUser(); 
     * 和 EasyFactory.createLogin(); 方法来获得具体的数据库访问类的实例，客户端代码上没有出现任何一个 MySQL 或 Oracle 的字样，达到了解耦的目的，
     * 客户端已经不再受改动数据库访问的影响了。
     * <p>
     * 在使用反射之后，我们还是需要进easyFactory中修改数据库类型，还不是完全符合开-闭原则。
     * <p>
     * 我们可以通过配置文件来达到目的，每次通过读取配置文件来知道我们应该使用哪种数据库。
     */
    static void TestEasyFactory() throws Exception {
        User user = new User();
        Login login = new Login();

        // 直接得到实际的数据库访问实例，而不存在任何依赖
        IUser userOperation = EasyFactory.createUser();
        userOperation.getUser(1);
        userOperation.insert(user);

        // 直接得到实际的数据库访问实例，而不存在任何依赖
        ILogin loginOperation = EasyFactory.createLogin();
        loginOperation.insert(login);
        loginOperation.getLogin(1);
    }

    public static void main(String[] args) throws Exception {
        TestAbstractFactory();
        System.out.println();

        TestEasyFactory();
    }
}

