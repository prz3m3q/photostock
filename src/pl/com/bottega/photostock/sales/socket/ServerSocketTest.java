package pl.com.bottega.photostock.sales.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.IntUnaryOperator;

public class ServerSocketTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while(true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while (!(line = bufferedReader.readLine()).trim().equals("")) {
                        System.out.println(line);
                    }
                    OutputStream outputStream = socket.getOutputStream();
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Content-Type: text/html; charset=utf-8");
                    printWriter.println("\r\n");
                    printWriter.println("Cześć Ktoś " + Math.random());
                    printWriter.println(Thread.currentThread().getName());
                    printWriter.flush();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
