package exceptionAndSocket;

import java.io.*;

/**
 * Created by caoquan on 1/18/17.
 */
public class InputThread extends Thread {
    private InputStream in;

    public InputThread(InputStream in) {
        this.in = in;
    }

    public void run() {
        while (true) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF8"), 1024);
                StringBuffer sb = new StringBuffer();
                int c;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }
                if (sb.length() > 0){
                    System.out.println(sb);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
