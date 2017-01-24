package tp3;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Created by caoquan on 1/24/17.
 */
public class Server {
    private byte[] digest;
    private SSLServerSocket serverSocket;

    public Server(String password) {

        // 1) encryption factory specification
        KeyManagerFactory kmf = null;
        SSLContext context = null;

        try {
            context = SSLContext.getInstance("SSLv3");
            kmf = KeyManagerFactory.getInstance("SunX509");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 2) key manager specification
        KeyStore ks = null;
        try {
            ks = KeyStore.getInstance("JKS");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        // 3) retrieving the certificate (and the key)
        char[] passPhrase = password.toCharArray();
        try {
            ks.load(new FileInputStream("/Users/caoquan/Documents/workspace/Thread Share/keys/Fichier_Certif"), passPhrase);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        // 4) Parameterization of the encryption factory by the key
        try {
            kmf.init(ks, passPhrase);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        // 5) specifying the context for generating SSLServerSocket
        try {
            context.init(kmf.getKeyManagers(), null, null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        // 6) creation of the SSLServerSocket factory
        SSLServerSocketFactory factory = context.getServerSocketFactory();

        // 7) create SSLServerSocket
        try {
            this.serverSocket = (SSLServerSocket) factory.createServerSocket(Constant.PORT);
            System.out.println("Create Socket OK");
        } catch (IOException e) {
            System.out.println("Error creating server socket:" + e);
            System.exit(1);
        }


    }


    public void listenTocalculateDigest() {
        try {
//            this.serverSocket = new ServerSocket(Constant.PORT);
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
        Server server = new Server(Constant.PASSWORD);
        server.listenTocalculateDigest();
    }
}
