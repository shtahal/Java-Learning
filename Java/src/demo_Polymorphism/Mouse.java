package demo_Polymorphism;

public class Mouse implements USB {
    @Override
    public void open() {
        System.out.println("Open the mouse!");
    }

    @Override
    public void close() {
        System.out.println("Close the mouse!");
    }

    public void use(){
        System.out.println("Click the mouse!");
    }

}
