package demo_Polymorphism;

public class PolymoDemo {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.powerOn();

        /*
        多态
            左父右子
            对于成员方法: 编译看左, 实现看右
            对于成员变量: 只能看父类

        向上转型
            统一的接口，实现具体的继承类
         */
        USB useMouse = new Mouse();
        computer.useDevice(useMouse);

        USB useKeyboard = new Keyboard();
        computer.useDevice(useKeyboard);

        computer.powerOff();
    }

}
