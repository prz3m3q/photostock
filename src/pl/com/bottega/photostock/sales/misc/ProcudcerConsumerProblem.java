package pl.com.bottega.photostock.sales.misc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProcudcerConsumerProblem {

    static class Producent implements Runnable {

        private Warehouse2 warehouse;

        public Producent(Warehouse2 warehouse) {
            this.warehouse = warehouse;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000 + (long)Math.random() * 10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String product = String.valueOf((int) (Math.random() * 100));
                System.out.println("Wyprodukowałem: " + product);
                warehouse.put(product);
            }
        }
    }

    static class Warehouse {

        private Queue<String> products = new LinkedList<>();

        public synchronized void put(String product) {
            products.add(product);
            notify();
        }

        public synchronized String take() {
            if (products.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String procudt = products.poll();
            return procudt;
        }
    }

    static class Warehouse2 {

        private BlockingQueue<String> products = new LinkedBlockingQueue<>();

        public void put(String product) {
            products.add(product);
        }

        public String take() {
            try {
                return products.take();
            } catch (InterruptedException e) {
                return take();
            }
        }
    }

    static class Consumer implements Runnable {

        private Warehouse2 warehouse;

        public Consumer(Warehouse2 warehouse) {
            this.warehouse = warehouse;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("Produktów w magazynie: " + warehouse.products.size());
                String product = warehouse.take();
                try {
                    Thread.sleep(1000 + (long)Math.random() * 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Skonsumowałem " + product + " produktów w magazynie: " + warehouse.products.size());
            }
        }
    }

    private static final int PRODUCENT_COUNT = 10;
    private static final int CONSUMENT_COUNT = 10;

    public static void main(String[] args) {
        Warehouse2 warehouse = new Warehouse2();
        for (int i = 0; i < PRODUCENT_COUNT; i++) {
            new Thread(new Producent(warehouse)).start();
        }
        for (int i = 0; i < CONSUMENT_COUNT; i++) {
            new Thread(new Consumer(warehouse)).start();
        }
    }

}
