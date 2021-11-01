package zuulremote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
    List<ClientConnection> players = new ArrayList<>();

    public static void main(String[] args) {
        int port = 9999;
        if (args.length > 0)
            port = Integer.parseInt(args[0]);
        try {
            new Server().listen(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void listen(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                System.out.println("Zuul Server waiting for connection on port "
                        + port);
                Socket socket = serverSocket.accept();
                System.out.println("Client connected from "
                        + socket.getInetAddress());
                players.add(new ClientConnection(socket));
            }
        }
    }

}
