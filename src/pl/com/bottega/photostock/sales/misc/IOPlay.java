package pl.com.bottega.photostock.sales.misc;

import java.io.*;

public class IOPlay {

    public static void main(String[] args) {
//        basicRead();
//        basicReadTryWithResources();
//        characterRead();
//        bufferedReader();
//        basicWrite();
//        writer();
//        printWriter();
        writeObjects();
        readObjects();
    }

    static class Person implements Serializable {
        int age;
        String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    private static void writeObjects() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/home/przemek/objects.bin"))) {
            oos.writeObject(new Person(12, "Jan Nowak"));
            oos.writeObject(new Person(18, "Jana Nowakowski"));
            oos.writeObject(new Person(20, "Janina Nowaki"));
            oos.writeObject(new Person(33, "Janowek Nowaku"));
            oos.writeObject(new Person(44, "Janko Nowaka"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readObjects() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/home/przemek/objects.bin"))) {
            Object o;
            while ((o = ois.readObject()) != null) {
                Person p = (Person) o;
                System.out.println(String.format("%s %d", p.name, p.age));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException IOException) {

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printWriter() {
        try (OutputStream outputStream = new FileOutputStream("/home/przemek/test1.txt"); PrintStream ps = new PrintStream(outputStream)) {
            ps.println("Zażółć gęślą jaźń");
            ps.println("Zażółć gęślą jaźń");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writer() {
        try (OutputStream outputStream = new FileOutputStream("/home/przemek/test1.txt"); Writer writer = new OutputStreamWriter(outputStream)) {
            writer.write("Zażółć gęślą jaźń");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void basicWrite() {
        try (OutputStream outputStream = new FileOutputStream("/home/przemek/test1.txt")) {
            outputStream.write("Zażółć gęślą jaźń".getBytes());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void bufferedReader() {
        try (InputStream inputStream = new FileInputStream("/home/przemek/test.txt")) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
//                System.out.print((char) 7);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku.");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

//    private static void characterRead() {
//        try (InputStream inputStream = new FileInputStream("/home/przemek/test.txt")) {
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            System.out.println(inputStreamReader.getEncoding());
//            int c;
//            while ((c = inputStreamReader.read()) != -1) {
//                System.out.print((char) c);
////                System.out.print((char) 7);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Nie ma takiego pliku.");
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    private static void basicReadTryWithResources() {
//        //wirtualna maszyna spróbuje zakmnąć strumień nie trzeba pisać finally
//        try (InputStream inputStream = new FileInputStream("/home/przemek/test.txt")) {
//            int b;
//            while ((b = inputStream.read()) != -1) {
//                System.out.print((char) b);
////                System.out.print((char) 7);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Nie ma takiego pliku.");
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    private static void basicRead() {
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream("/home/przemek/test.txt");
//            int b;
//            while ((b = inputStream.read()) != -1) {
//                System.out.print((char) b);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Nie ma takiego pliku.");
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println();
//        } finally {
//            if (inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
