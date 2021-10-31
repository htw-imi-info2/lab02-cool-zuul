package zuulremote;

import zuul.Game;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public  class Server {
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
                while (true) {
                    System.out.println("Zuul Server waiting for connection on port "
                            + port);
                    try (ServerSocket serverSocket = new ServerSocket(port);
                         Socket socket = serverSocket.accept();) {
                        System.out.println("Client connected from "
                                + socket.getInetAddress());
                        Game player = new Game("data/zuul.yml",socket.getInputStream(),new PrintStream(socket.getOutputStream()));
                        player.play();
                    }
                }

    }

}
