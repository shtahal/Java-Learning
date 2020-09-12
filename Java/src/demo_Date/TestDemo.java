package demo_Date;

/**
 *  计算一个人到现在已经出生多少天
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TestDemo {

    public static void main(String[] args) throws ParseException {
        // 输入出生日期
        String birth = new Scanner(System.in).next();
        // 使用SimpleDateFormat的parse方法解析字符串时间为Date格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = sdf.parse(birth);
        // 将Date格式转换为毫秒值
        long birthDateTime = birthDate.getTime();
        // 获取当前日期的毫秒值
        long curTime = new Date().getTime();
        // 相减并得到出生多少天
        System.out.println((curTime - birthDateTime) / 1000 / 60 / 60 / 24);
    }

}
