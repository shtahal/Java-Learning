package demo_FileAndIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class test {

    public static void main(String[] args) throws IOException {
        // 创建ObjectOutputStream对象, 构造函数中传递字节输出流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\demo_FileAndIO\\wobject.txt"));
        // 使用writeObject()方法将对象信息写入文件中
        oos.writeObject(new Person("黎明", 35));
        // 释放资源
        oos.close();
    }

}
