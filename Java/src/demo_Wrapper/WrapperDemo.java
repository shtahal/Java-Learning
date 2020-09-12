package demo_Wrapper;

/*

    包装类 java.lang.***

    基本类型: 使用方便      引用类型: 像类一样操作基本类型, 使用更多功能
        boolean             Boolean
        char                Character
        byte                Byte
        short               Short
        int                 Integer
        long                Long
        float               Float
        double              Double

    装箱: 基本类型   -> 对应的包装类对象
        a. 使用包装类的构造方法, 直接构造对象  ex. Integer(int value / String s)
        b. 使用静态方法   ex. static Integer valueOf(int i / String s)
    拆箱: 包装类对象 -> 对应的基本类型
        a. 使用成员方法   ex. int intValue()

    基本类型转换为String: 基本类型 -> 字符串
        a. 基本类型数据的值+""(最简单, 最常见)
        b. 使用包装类中的静态方法
            ex. static String toString(int i)   返回指定类型的String对象
        c. 使用String类中的静态方法
            ex. static String valueOf(int i)    返回指定类型的字符串表示
    String转换为基本类型: 字符串 -> 基本类型
        a. 使用包装类的静态方法
            ex. public static int parseInt(String s)
        b. 除了Character类没有
 */

public class WrapperDemo {
    public static void main(String[] args) {
        Integer i1 = new Integer(1);    // 装箱
//        Integer i1 = 1;                      // 自动装箱
        System.out.println(i1);

        Integer i2 = Integer.valueOf(2);
        System.out.println(i2);

        // 拆箱
        int i = i2.intValue();
        System.out.println(i);
        System.out.println("=================");

        // 基本类型 -> 字符串
        String str1 = 10+"";
        String str2 = Integer.toString(20);
        String str3 = String.valueOf(30);
        // 字符串 -> 基本类型
        Integer.parseInt("40");

    }
}
