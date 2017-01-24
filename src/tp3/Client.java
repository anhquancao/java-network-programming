package tp3;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by caoquan on 1/24/17.
 */
public class Client {
    public static void main(String[] args) {
        String[] filenames = {"/Users/caoquan/Desktop/5d6550f83cd22efdf437fca6760c9ee4a8893a43.png",
                "/Users/caoquan/Desktop/5d6550f83cd22efdf437fca6760c9ee4a8893a43.png"};
        Socket socket = null;
        try {
            socket = new Socket("localhost", Constant.PORT);
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
}
