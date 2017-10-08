package pl.com.bottega.photostock.sales.misc;

public class ThreadTest {

    private static int x;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                synchronized (Thread.class) {
                    x += 1;
                }
//                incX();
                System.out.println(x);
            }
        });

        thread.start();

        for (int i = 0; i < 30; i++) {
//            incX();
            synchronized (Thread.class) {
                x += 1;
            }
            System.out.println(String.format("Poza wątkiem %s", x));
        }
    }

    private static synchronized void incX() {
        x += 1;
    }
}
