package tp3.ex2;

import tp3.ex2.up13.monftp.FTPContent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by caoquan on 1/31/17.
 */
public class TestURLHandler {
    public static void main(String[] args) {
        String urlStr = "monftp://monLogin:monPaswwd@www.ig-edu.univ-paris13.fr?monFichier.txt";
        System.getProperties().put("java.protocol.handler.pkgs", "tp3.ex2.up13");
        try {
            URL url = new URL(urlStr);
            FTPContent content = (FTPContent) url.getContent();

            System.out.println(content);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
