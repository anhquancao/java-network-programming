package tp3;


import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.Security;

/**
 * Created by caoquan on 1/24/17.
 */
public class ClientSecure {
    private SSLSocket socket;

    public ClientSecure(String host) throws IOException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        System.setProperty("javax.net.ssl.trustStore", "jssecacerts");
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        socket = (SSLSocket) factory.createSocket(host, Constant.PORT);
    }

    public void run() {
        String[] filenames = {"/Users/caoquan/Desktop/5d6550f83cd22efdf437fca6760c9ee4a8893a43.png",
                "/Users/caoquan/Desktop/5d6550f83cd22efdf437fca6760c9ee4a8893a43.png"};
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Constant.CHAR_SET), Constant.BUFFER_SIZE);

            for (int i = 0; i < filenames.length; i++) {
                writer.write(filenames[i]);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ClientSecure clientSecure = new ClientSecure("localhost");
            clientSecure.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
