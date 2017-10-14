package pl.com.bottega.photostock.sales.socket;

import java.io.*;
import java.net.Socket;

public class SocketTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("www.pollub.pl", 80);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println("GET / HTTP/1.1");
        printWriter.println("Host: www.pillub.pl");
        printWriter.println("\r\n");
        printWriter.flush();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
    }
}
