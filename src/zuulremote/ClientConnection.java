package zuulremote;

import zuul.Game;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ClientConnection {
    Game player;
    Socket socket;
    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.player = new Game("data/zuul.yml",socket.getInputStream(),new PrintStream(socket.getOutputStream()));
        startGame();
    }

    private void startGame() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    player.play();
            }
        }).start();
    }
}
