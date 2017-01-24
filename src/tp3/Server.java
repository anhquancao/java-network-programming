package tp3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by caoquan on 1/24/17.
 */
public class Server {
    private byte[] digest;
    private ServerSocket serverSocket;


    public Server() {
    }


    public void listenTocalculateDigest() {
        try {
            this.serverSocket = new ServerSocket(Constant.PORT);
            System.out.println("Server is listening at " + Constant.PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Socket accept");
                Thread t = new Thread(new InputRunnable(socket.getInputStream()));
                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        server.listenTocalculateDigest();
    }
}
