package demo_FileAndIO;

/*

                输入流             输出流
    字节流     InputStream     OutputStream
    字符流       Reader            Writer

    字节输出流 java.io.OutputStream(抽象超类)
        1. 将字节从内存写出到硬盘
        2. 常用方法
            public void close():                            关闭输出流并释放相关的所有系统资源, 必须调用该方法
            public void flush():                            刷新输出流并强制任何缓冲的输出字节被写出
            public void write(byte[] b):                    将指定的字节数组写入到输出流
            public void write(byte[] b, int off, int len):  将指定产犊的字节数组, 从off位置开始写入到输出流
    文件字节输出流 java.io.FileOutputStream extends OutputStream

    字节输入流 java.io.InputStream(抽象超类)
        1. 读取输入流到内存中
        2. 常用方法
            public void close():                            关闭输入流并释放相关的所有系统资源, 必须调用该方法
            public abstract int read():                     从输入流读取下一个字节, 读取到文件末尾返回-1
            public void read(byte[] b):                     从输入流读取数据, 并存储到字节数组b中
    文件字节输入流 java.io.FileInputStream extends InputStream

    字符输出流 java.io.Writer(超类)
        1. 常用方法
            public void flush():                            刷新输出流的缓冲
            public void close():                            关闭输出流并释放相关的所有系统资源, 必须调用该方法
            void write(int c):                              写入单个字符
            void write(char[] cbuf):                        写入字符数组
            void write(char[] cbuf, int off, int len)
            void write(String str):                         写入字符串
            void write(String str, int off, int len)
    文件字符输出流 java.io.FileWriter extends OutputStreamWriter extends Writer

    字符输入流 java.io.Reader(超类)
        1. 一个GBK中文字符占两个字节, UTF-8中文字符占三个字节, 使用字节流读取有可能出问题
        2. 常用方法
            public void close():                            关闭输入流并释放相关的所有系统资源, 必须调用该方法
            public int read():                              从输入流读取下一个字符
            public int read(char[] cbuf):                   从输入流读取一些字符, 并存储到字符数组cbuf中
    文件字符输入流 java.io.FileReader extends InputStreamReader extends Reader
        底层还是先使用FileInputStream根据默认的编码集将字符转换为字节, 然后使用FileReader将字节转换为字符

    属性集 java.util.Properties extends Hashtable<k, v> extends Map<k, v>
        1. Properties集合是唯一一个和IO流相结合的集合, key,value默认都是String
        2. 常用方法
            void store(Writer writer, String comments):     把集合中的临时数据持久化到硬盘中
            void load(InputStream inStream):                把硬盘中存储的文件(键值对)读取到集合中
            Object setProperty(String key, String value):   调用Hashtable的put()方法
            Object getProperty(String key):                 相当于Hashtable的get()方法
            Set<String> stringPropertyNames():              返回属性列表中的键集, 相当于Map集合的keySet()方法

    缓冲流(高效流)
        1. 在创建对象时, 会创建一个内置默认大小的缓冲区数组; 通过缓冲区的读写, 减少系统IO次数, 提高效率
        2. 分类
            字节缓冲流   BufferedInputStream, BufferedOutputStream
            字符缓冲流   BufferedReader, BufferedWriter

    读写乱码分析
        1. GBK格式使用两个字节表示一个中文, IDE默认的UTF-8格式使用三个字节表示一个中文
        2. FileReader在读取GBK格式的中文字符时, 底层使用FileInputStream先将本地文件上的字符转换为字节, 其中每个字符对应两个字节
        3. 然而FileReader只能查询IDE默认的编码表, 在读取到内存时再将字节转换字符, 这时UTF-8编码格式则是三个字节表示一个字符
        4. 出现乱码

    转换流
        java.io.InputStreamReader extends Reader
            1. InputStreamReader是字节流通向字符流的桥梁, 不仅可以查询IDE默认的编码表, 还可以通过指定编码查询码表
        java.io.OutputStreamWriter extends Writer
            1. OutputStreamWriter是字符流通向字节流的桥梁, 可以通过指定编码集将字符转换为字节

    序列化和反序列化
        ObjectOutputStream: 对象的序列化, 将对象以字节流的方式写入到文件中, 实现对象的持久存储
            1. 对象类必须实现java.io.Serializable接口, 否则会抛出NotSerializableException
            2. 对象类的所有属性必须是可序列化的, 若某个属性不需要可序列化, 必须使用transient关键字修饰为瞬态的
        ObjectInputStream:  对象的反序列化
            1. 类必须实现Serializable接口
            2. 必须存在类对应的.class文件, 否则抛出ClassNotFoundException异常
        transient关键字: 瞬态关键字
            被transient修饰的成员变量, 不能被序列化
        常见问题: InvalidClassException
            1. 类实现Serialization接口, 编译器赋予.class文件一个序列号, 序列化时同时会将序列号赋予给将对象保存到的文件中;
               反序列化时, 会将.class和文件的序列号进行对比; 若相同则进行序列化, 反之抛出异常;
            2. 当修改了类中属性方法的定义, 编译器将重新生成序列号; 若没有再次进行序列化, .class和文件的序列号不同, 反序列化时抛出异常
            3. 解决方法: 手动定义序列号; 反序列化前先进行序列化

 */

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class FileAndIODemo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        demo1();    // FileOutputStream类的使用
//        demo2();    // 在文件内容后追加新内容, 并回车
//        demo3();    // FileInputStream类读入一个字节
//        demo4();    // FileInputStream类读入多个字节
//        demo5();    // 使用字符流读取并写入(复制)一张图片
//        demo6();    // FileWriter类的使用
//        demo7();    // try...catch处理异常
//        demo8();    // 属性集的常用方法
//        demo9();    // 缓冲流
//        write_utf8();   // OutputStreamWriter写入UTF-8格式的中文
//        write_gbk();   // OutputStreamWriter写入GBK格式的中文
//        read_utf8();    // InputStreamReader读取UTF-8格式的中文
//        read_gbk();    // InputStreamReader读取GBK格式的中文
//        Convert_GBK2UTF8(); // 将GBK格式文件转换为UTF-8格式文件
//        demo10();   // ObjectOutputStream序列化对象
        demo11();   // ObjectInputStream反序列化对象
        demo12();   //

    }

    private static void demo12() {

    }

    private static void demo11() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src\\demo_FileAndIO\\wobject.txt"));
        Object o = ois.readObject();
        ois.close();
        System.out.println(o);

        Person p = (Person) o;
        System.out.println(p.getAge() + " " + p.getName());
    }

    private static void demo10() throws IOException {
        // 创建ObjectOutputStream对象, 构造函数中传递字节输出流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src\\demo_FileAndIO\\wobject.txt"));
        // 使用writeObject()方法将对象信息写入文件中
        oos.writeObject(new Person("黎明", 35));
        // 释放资源
        oos.close();
    }

    private static void Convert_GBK2UTF8() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("src\\demo_FileAndIO\\gbk.txt"), "gbk");
        OutputStreamWriter isw = new OutputStreamWriter(new FileOutputStream("src\\demo_FileAndIO\\gbk2utf8.txt"), "UTF-8");
        int len = 0;
        while ((len = isr.read()) != -1) {
            isw.write(len);
        }
        isw.close();
        isr.close();
    }

    private static void read_gbk() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("src\\demo_FileAndIO\\gbk.txt"), "gbk");
//        InputStreamReader isr = new InputStreamReader(new FileInputStream("src\\demo_FileAndIO\\gbk.txt"), "utf-8");  // 乱码
        int len = 0;
        while ((len = isr.read()) != -1) {
            System.out.print((char)len);
        }
        isr.close();
    }

    private static void read_utf8() throws IOException {
        // 创建InputStreamReader对象, 构造方法中传入字节输入流和指定的编码格式; 编码格式要和文件的编码格式相同, 否则会乱码
//        InputStreamReader isr = new InputStreamReader(new FileInputStream("src\\demo_FileAndIO\\utf8.txt"), "utf-8");
        InputStreamReader isr = new InputStreamReader(new FileInputStream("src\\demo_FileAndIO\\utf8.txt"), "gbk");
        // 使用read()方法读取文件
        int len = 0;
        while ((len = isr.read()) != -1) {
            System.out.print((char)len);
        }
        // 释放资源
        isr.close();
    }

    private static void write_gbk() throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("src\\demo_FileAndIO\\gbk.txt"), "GBK");
        osw.write("指定编码格式");
        osw.flush();
        osw.close();
    }

    private static void write_utf8() throws IOException {
        // 创建OutputStreamWriter对象, 构造函数中传入字节输出流和指定的编码表
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("src\\demo_FileAndIO\\utf8.txt"), "utf-8");
        // 使用write()方法将字符转换为字节存储到内存的缓冲区中
        osw.write("指定编码格式");
        // 使用flush()方法将缓冲区中的字节刷新到文件中
        osw.flush();
        // 释放资源
        osw.close();
    }

    private static void demo9() throws IOException {
        FileOutputStream fos = new FileOutputStream("src\\demo_FileAndIO\\d.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write("使用字节缓冲输出流".getBytes());
        bos.flush();
        bos.close();

        FileInputStream fis = new FileInputStream("src\\demo_FileAndIO\\d.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        int len = 0;
        while ((len = bis.read()) != -1) {
            System.out.println(len);
        }
        bis.close();
    }

    private static void demo8() throws IOException {
        Properties prop = new Properties();
        // Object setProperty(String key, String value)
        prop.setProperty("Lisa", "18");
        prop.setProperty("Jerry", "28");
        prop.setProperty("杰伦", "22");
        // Object setProperty(String key, String value)
        Set<String> set = prop.stringPropertyNames();
        // Object getProperty(String key)
        for (String key : set) {
            String value = prop.getProperty(key);
            System.out.println(key + " " + value);
        }
        /*
            void store(OutputStream out, String comments)
            void store(Writer writer, String comments)
            参数：
                OutputStream out:   字节输出流, 不能写入中文
                Writer writer:      字符输出流, 可以写入中文
                String comments:    注释, 用来即使说明文件的作用, 会随数据写到文件中; 使用中文会产生乱码, 默认Unicode编码; 一般使用""空字符串
         */
        FileWriter fw = new FileWriter("src\\demo_FileAndIO\\c.txt");
        prop.store(fw, "store data");
        fw.close();
        /*
            void load(InputStream inStream)
            void load(Reader reader)
            参数:
                InputStream inStream:   字节输入流, 不能读取含有中文的键值对
                Reader reader:          字符输出流, 可以读取含有中文的键值对
            注意:
                存储键值对的文件中, 键与值默认的连接符号可以使用=,空格(其他符号)
                存储键值对的文件中, 可以使用#进行注释, 注释后的键值对不会被读取
                存储键值对的文件中, 键与值默认都是字符串, 不用加引号
         */
        FileReader fr = new FileReader("src\\demo_FileAndIO\\c.txt");
        prop.load(fr);
        Set<String> set1 = prop.stringPropertyNames();
        for (String key : set1) {
            String value = prop.getProperty(key);
            System.out.println(key + "=" + value);
        }
        fr.close();
    }

    private static void demo7() {
        // 进行初始化, 否则finally中fw无法使用; 同时避免fw = new FileWriter(...)运行失败导致fw没有值, 无法进行close()
        FileWriter fw = null;
        try {
            fw = new FileWriter("src\\demo_FileAndIO\\b.txt");
            fw.write(97);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            // 创建对象失败, fw仍是默认值null, 而null不能调用资源, 否则抛出NullPointerException, 所以进行一个判断
            if (fw == null) {
                try {
                    // fw.close()也抛出了IO异常, 同样需要进行处理
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void demo6() throws IOException {
        FileWriter fw = new FileWriter("src\\demo_FileAndIO\\b.txt");
        fw.write(97);
        // 与字节输出流不同的是, 如果未使用close()方法, 那么必须需要使用flush()方法将内存缓冲区的数据刷新到目标文件中
        fw.flush();

        char[] chars = {'e', 'f', 'g'};
        fw.write(chars);
        fw.flush();

        fw.write("从第十三个字符开始写入两个中文字符串", 13, 2);
        fw.close();
    }

    private static void demo5() throws IOException {
        // 创建字节输入流对象
        FileInputStream fis = new FileInputStream("src\\demo_FileAndIO\\steam.png");
        // 创建字节输出流对象
        FileOutputStream fos = new FileOutputStream("src\\demo_FileAndIO\\steam_cp.png");

        // 一次读取文件的一个字节
        /*
        int len = 0;
        while ((len = fis.read()) != -1) {
            // 写入新的文件
            fos.write(len);
        }
         */

        // 使用字节数组缓冲读取多个字节(推荐), 每次写入有效个字节数
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = fis.read(b)) != -1) {
            fos.write(b, 0, len);
        }

        // 关闭资源, 先关闭输出流, 后关闭输入流; 因为输出完了肯定已经输入完了
        fos.close();
        fis.close();
    }

    private static void demo4() throws IOException {
        FileInputStream fis = new FileInputStream("src\\demo_FileAndIO\\a.txt");
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = fis.read(b)) != -1) {
            System.out.println(new String(b, 0, len));
        }
        fis.close();
    }

    private static void demo3() throws IOException {
        // 创建FileInputStream类对象, 构造方法中传入要读取文件的路径
        FileInputStream fis = new FileInputStream("src\\demo_FileAndIO\\a.txt");
        // 使用read()方法读取文件中的一个字节
        int i = 0;
        while ((i = fis.read()) != -1) {
            System.out.println(i);
        }
        // 读到文件末尾返回-1
        i = fis.read();
        System.out.println(i);
        // 关闭资源
        fis.close();
    }

    private static void demo2() throws IOException {
        FileOutputStream fos = new FileOutputStream("src\\demo_FileAndIO\\a.txt", true);
        for (int i = 0; i < 2; i++) {
            // 使用getBytes()方法将字符串转为字节表示
            fos.write("BC".getBytes());
            // 换行
            fos.write("\r\n".getBytes());
        }
        fos.close();
    }

    private static void demo1() throws IOException {
        // 创建FileOutputStream类的对象, 构造方法中传入要写到指定文件的路径
        FileOutputStream fos = new FileOutputStream("src\\demo_FileAndIO\\a.txt");
        // 使用write()方法, 将数据写到文件中
        fos.write(97);
        // 释放文件资源
        fos.close();
    }

}
