package pl.com.bottega.photostock.sales.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class ChatServer {

    private List<Client> clients = new Vector<>();

    public static void main(String[] args) throws IOException {
        new ChatServer().work();
    }

    public void work() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6661);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Client client = new Client(this, clientSocket.getInputStream(), clientSocket.getOutputStream());
            client.sendMessage("Witaj!", client);
            clients.add(client);
            new Thread(client).start();
        }
    }

    static class Client implements Runnable {

        private final ChatServer server;
        private BufferedReader bufferedReader;
        private PrintWriter printWriter;
        private String name;

        public Client(ChatServer server, InputStream inputStream, OutputStream outputStream) {
            this.server = server;
            this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            this.printWriter = new PrintWriter(outputStream);
        }

        @Override
        public void run() {
            try {
                name = bufferedReader.readLine();
                if (name == null) {
                    server.clientDisconnected(this);
                    return;
                }
                while (true) {
                    String msg = bufferedReader.readLine();
                    if (msg == null) {
                        server.clientDisconnected(this);
                        return;
                    }
                    server.newMessage(msg, this);
                }
            } catch (IOException e) {
                server.clientDisconnected(this);
            }
        }

        public void sendMessage(String msg, Client author) {
            try {
                printWriter.println(String.format("%s > %s", author.name, msg));
                printWriter.flush();
            } catch (Exception ex) {}
        }
    }

    private void clientDisconnected(Client client) {
        this.clients.remove(client);
    }

    private void newMessage(String msg, Client author) {
        for (Client otherClient : clients) {
//            if (author != otherClient) {
                otherClient.sendMessage(msg, author);
//            }
        }
    }
}
