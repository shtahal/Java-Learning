package demo_Thread;

public class CommonMethods extends Thread {
    @Override
    public void run() {
        // a.
//        String name = getName();
//        System.out.println(name);

        // b.
        Thread t = Thread.currentThread();
        System.out.println(t);
    }
}
