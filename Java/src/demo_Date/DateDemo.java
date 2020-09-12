package demo_Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * java.text.DateFormat
 *      抽象类, 作为父类的存在, 需创建子类 SimpleDateFormat 的对象进行使用
 *
 * java.text.SimpleDateFormat extends DateFormat
 *      1. 功能: 格式化（日期 →文本）, 解析（文本 →日期）
 *      2. 构造方法 SimpleDateFormat(String pattern)
 *          使用给定的模式, 区分大小写
 *              y   年
 *              M   月
 *              d   日
 *              H   时
 *              m   分
 *              s   秒
 *          "yyyy-MM-dd HH:mm:ss" "yyyy年MM月dd日 HH时mm分ss"
 *       3. 常用方法
 *          String format(Date date)    按给定的模式, 将Date日期格式化为字符串
 *          Date parse(String source)     解析文本字符串为Date日期
 *
 * java.util.Calendar
 *      1. 抽象类, 提供操作日历字段的方法, 日历字段可通过Calendar静态变量获取
 *              YEAR            年
 *              MONTH           月
 *              DAY_OF_MONTH    月中的天(几号)
 *              HOUR            时(12小时制)
 *              HOUR_OF_DAY     时(22小时制)
 *              MINUTE          分
 *              SECOND          秒
 *      2. 无法直接创建对象, 使用其静态方法 static Calendar getInstance(), 返回Calendar的子类对象
 *      3. 常用方法
 *          public int get(int field): 返回给定日历的字段值, 是西方的时间方式
 *          public void set(int field, int value): 设置指定时间
 *          public abstract void add(int field, int amount): 正数增加时间, 负数减少时间
 *          public Date getTime(): 返回表示Calendar时间的Date对象
 *
 *
 */

public class DateDemo {

    public static void main(String[] args) throws ParseException {
        method1();  // SimpleDateFormat format
        method2();  // SimpleDateFormat parse
        method3();  // Calendar

    }

    // Calendar
    private static void method3() {
        // static Calendar getInstance()
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar/*.toString()*/);    // 重写了toString()方法

        // public int get(int field)
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "-" + month + "-" + day);

        // public Date getTime()
        Date time = calendar.getTime();
        System.out.println(time);
    }

    /*
        Date parse(String text)     解析文本字符串为Date日期
            1. parse方法声明了ParseException异常, 若时间模式和给定模式不匹配, 则会抛出异常
            2. 调用声明了异常的方法, 就要处理这个异常
                a. 继续声明throws抛出异常
                b. try...catch处理异常
     */
    private static void method2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2020-08-21 09:00:36");
        System.out.println(date);   // Fri Aug 21 09:00:36 CST 2020
    }

    // String format(Date date)    按给定的模式, 将Date日期格式化为字符串
    private static void method1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String time = sdf.format(date);
        System.out.println(date);   // Fri Aug 21 09:00:36 CST 2020
        System.out.println(time);   // 2020-08-21 09:00:36
    }

}
