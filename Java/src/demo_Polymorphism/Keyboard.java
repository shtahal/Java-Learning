package demo_Polymorphism;

public class Keyboard implements USB {
    @Override
    public void open() {
        System.out.println("Open the keyboard!");
    }

    @Override
    public void close() {
        System.out.println("Close the keyboard!");
    }

    public void use(){
        System.out.println("Click the keyboard!");
    }

}
