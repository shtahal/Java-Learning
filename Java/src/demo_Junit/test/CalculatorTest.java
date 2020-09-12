package demo_Junit.test;

import demo_Junit.mainClass.Calculator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    /*
        初始化方法
        用于资源申请, 所有测试方法在执行之前都会先执行该方法
     */
    @Before
    public void init() {
        System.out.println("init...");
    }

    /*
        释放资源方法
        让所有测试方法在执行之后, 都会自动执行该方法
     */
    @After
    public void close() {
        System.out.println("close...");
    }

    @Test
    public void testAdd() {
        System.out.println("测试是否在初始化和关闭资源中间");
        Calculator c = new Calculator();
        int res = c.add(3, 6);
//        System.out.println(res);
        Assert.assertEquals(9, res);
    }

}
