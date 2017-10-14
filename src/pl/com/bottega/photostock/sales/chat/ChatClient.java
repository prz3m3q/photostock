package pl.com.bottega.photostock.sales.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static final int PORT = 6661;
    private static final String HOST = "192.168.1.32";
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private static Scanner scanner = new Scanner(System.in);
    private Socket socket;

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();
        String name = getName();
        chatClient.sendName(name);
        chatClient.startChatting();
    }

    public ChatClient() {
        try {
            socket = new Socket(HOST, PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startChatting() {
        new Thread(() -> {
            try {
                String clientLine;
                while ((clientLine = bufferedReader.readLine()) != null) {
                    System.out.println(clientLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            printWriter.println(scanner.nextLine());
            printWriter.flush();
        }
    }

    private void sendName(String name) {
        printWriter.println(name);
        printWriter.flush();
    }

    public static String getName() {
        System.out.print("Podaj swoje imię: ");
        return scanner.nextLine();
    }
}
