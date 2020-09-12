package demo_InnerClass;

/**
 * 内部类: 类似一个事物内包含另一个事物, 一个类内部包含另一个类
 *      手机和屏幕, 身体和心脏, 汽车和零件
 *
 * 1. 成员内部类
 *      a. 内部类可随意使用外部类的成员, 反之不可以
 *      b. 分别生成内部类和外部类各自的 .class文件
 *      c. 间接使用: 在外部类中使用内部类, 然后只使用外部类对象的方法
 *         直接使用: 外部类.内部类 obj = new 外部类().内部类()
 *      d. 成员内部类调用外部类重名变量: 外部类.this.变量名
 * 2. 局部内部类(包含匿名内部类)
 *      a. 一个类定义在一个方法内, 只有该方法可以使用该类
 *      b. 访问所在方法的局部变量时, 该局部变量是[有效final的], final可省略
 *          因为new的对象在堆中会持续存在, 直至垃圾回收才消失; 而局部变量在栈中, 方法运行结束后立刻出栈消失
 *    3. 匿名内部类
 *          a. 接口 obj = new 接口(){...};
 *          b. 若接口的实现类(或父类的子类)只需要使用一次, 则可直接省略该类的定义, 使用 [匿名类]
 *          c. 匿名内部类: 在创建对象时, 只能使用唯一的一次;
 *                      若想多次创建对象, 且类的内容一样的话, 必须使用单独定义的实现类
 *             匿名对象: 在调用方法时, 只能调用唯一一次;
 *                      若想使用同一对象调用多个方法, 必须给对象起个名称
 *             匿名内部类是省略了 [实现类/子类] 名称, 匿名对象是省略了 [对象名称]
 *
 *
 * 类的权限修饰符规则:
 *      public > protected > (default) > private
 *      1. 外部类: public / (default)
 *      2. 成员内部类: public / protected / (default) / private
 *      3. 局部内部类: 什么都不能写
 */

public class InnerClassDemo {

    public static void main(String[] args) {

        /*
        成员内部类
            1. 间接使用成员内部类
            2. 直接使用成员内部类
            3. 内部类的同名成员变量访问
         */
        // 1.
        Phone phone = new Phone();
        phone.show();
        System.out.println("===================");

        // 2.
        Phone.Screen ps = new Phone().new Screen();
        ps.use();
        System.out.println("===================");

        // 3.
        ps.showVar();
        System.out.println("===================");

        /*
        局部内部类
            1. 局部内部类的使用
            2. 局部内部类访问所在方法局部变量的使用
         */
        phone.use2();
        System.out.println("===================");

        /*
        匿名类
            1. 实现类和匿名类的使用
            2. 匿名对象的使用
         */
        // 1.
        AnonymousInterface objimpl = new AnonymousInterfaceImpl();  // 实现类
        objimpl.method();

        AnonymousInterface obj1 = new AnonymousInterface() {       // 匿名类
            @Override
            public void method() {
                System.out.println("匿名类的方法覆盖. 111-A");
            }
            @Override
            public void method1() {
                System.out.println("匿名类的方法覆盖. 222-A");
            }
        };
        obj1.method();
        obj1.method1();

        // 2. 使用匿名类, 并省略了匿名对象名称; 只能调用一个方法
        new AnonymousInterface() {
            @Override
            public void method() {
                System.out.println("匿名类的方法覆盖. 111-B");
            }
            @Override
            public void method1() {
                System.out.println("匿名类的方法覆盖. 222-B");
            }
        }.method();



    }

}
