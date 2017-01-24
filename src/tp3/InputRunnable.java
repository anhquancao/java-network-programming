package tp3;

import java.io.*;

/**
 * Created by caoquan on 1/24/17.
 */
public class InputRunnable implements Runnable {
    private InputStream in;

    public InputRunnable(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(this.in, Constant.CHAR_SET), Constant.BUFFER_SIZE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                String line = null;
                line = reader.readLine();
                if (line == null) {
                    break;
                } else {
                    Thread t = new Thread(new DigestRunnable(this.in, line));
                    t.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
