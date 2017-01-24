package tp2.ex2.digest3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by caoquan on 1/24/17.
 */
public class CbDigest implements Runnable {
    private File input;

    private CbDigestServer server;


    public CbDigest(File input, CbDigestServer server) {
        this.input = input;
        this.server = server;
    }

    @Override
    public void run() {
        synchronized (this.server) {
            try {
                System.out.println("task");
                MessageDigest sha = MessageDigest.getInstance("SHA");

                DigestInputStream din = new DigestInputStream(new FileInputStream(this.input), sha);
                int b;
                while ((b = din.read()) != -1) ;
                din.close();
                byte[] digest = sha.digest();
                this.server.setDigest(digest);
                this.server.notify();

            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }
        }

    }
}
