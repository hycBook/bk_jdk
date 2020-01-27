package per.hyc.designPattern.Bridge;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class GetProperties {
    public static Properties getPro(String pathname) {
        Properties pro = new Properties();
        File f = new File(pathname);
        try {
            if (f.exists()) {
                pro.load(new FileInputStream(f));
            }
            // 建立一个新的属性文件，同时设置好默认内容
            else {
                pro.setProperty("color", "per.hyc.designPattern.Bridge.Blue");
                pro.setProperty("pen", "per.hyc.designPattern.Bridge.SmallPen");
                pro.store(new FileOutputStream(f), "Bridge CLASS");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pro;
    }

    // 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public static Object getBean(String args) {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File(".\\src\\main\\resources\\configPen.xml"));
            Node classNode = null;
            NodeList nl = doc.getElementsByTagName("className");

            if (args.equals("color")) {
                //获取包含类名的文本节点
                classNode = nl.item(0).getFirstChild();
            } else if (args.equals("pen")) {
                //获取包含类名的文本节点
                classNode = nl.item(1).getFirstChild();
            }

            String cName = classNode.getNodeValue();
            //通过类名生成实例对象并将其返回
            return Class.forName(cName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}