package demo_InnerClass;

public class Phone {

    public class Screen {
        private int price = 50;   // 内部类的成员变量
        // 内部类同名成员变量访问
        public void showVar() {
            int price = 80; // 内部类方法的局部变量
            System.out.println(price);              // 80
            System.out.println(this.price);         // 50
            System.out.println(Phone.this.price);   // 30
        }

        // 内部类可调用外部类的所有成员
        public void use() {
            System.out.println(price);
//            show();
        }

    }

    private int price = 30; // 外部类的成员变量

    public void show() {
        System.out.println("I'm a phone.");
        new Screen().use(); // 间接使用内部类
    }

    // 1. 局部内部类的使用
    // 2. 局部内部类访问所在方法局部变量的使用
    public void use2() {
            /*final*/ int price = 100;
        class Screen2 {
            public void screen2use () {
                System.out.println(price);
            }
        }

        Screen2 s2 = new Screen2();
        s2.screen2use();
    }

}
