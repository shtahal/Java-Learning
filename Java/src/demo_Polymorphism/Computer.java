package demo_Polymorphism;

public class Computer {

    public void powerOn(){
        System.out.println("Power On!");
    }

    public void powerOff(){
        System.out.println("Power Off!");
    }

    public void useDevice(USB usb) {
        usb.open();
        /*
        向下转型
            使用实现类自己的成员方法
         */
        if (usb instanceof Mouse) {
            Mouse mouse = (Mouse) usb;
            mouse.use();
        } else if (usb instanceof Keyboard) {
            Keyboard keyboard = (Keyboard) usb;
            keyboard.use();
        }
        usb.close();
    }

}
