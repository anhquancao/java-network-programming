package tp3.ex2.up13.monftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ContentHandler;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by caoquan on 1/31/17.
 */
public class FPTContentHandler extends ContentHandler {
    String login;
    String password;
    String machine;
    String fileName;
    String file;

    @Override
    public Object getContent(URLConnection urlc) throws IOException {
        URL url = urlc.getURL();
        String[] userInfo = url.getUserInfo().split(":");
        login = userInfo[0];
        password = userInfo[1];
        machine = url.getHost();
        fileName = url.getQuery();
        setFile();
        return new FTPContent(login, password, machine, fileName, file);
    }

    private void setFile() throws IOException {
        String command = "cp " + fileName + " /Users/caoquan/tmp.txt";
        Process p = Runtime.getRuntime().exec(command);

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Interrupt");
        }

        file = "";

        InputStream in = new FileInputStream("/Users/caoquan/tmp.txt");

        int b;
        while ((b = in.read()) != -1) {
            file += (char) b;
        }
        in.close();

    }
}
