package tp2.ex2.digest1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            DigestInputStream din = new DigestInputStream(new FileInputStream(this.input), sha);
            int b;
            while ((b = din.read()) != -1);
            din.close();
            byte[] digest = sha.digest();
            this.server.setDigest(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
