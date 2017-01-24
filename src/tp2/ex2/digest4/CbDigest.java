package tp2.ex2.digest4;

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
public class CbDigest extends Thread {
    private File input;

    private byte[] digest;


    public CbDigest(File input) {
        this.input = input;
    }

    public String getFileName() {
        return this.input.getName();
    }

    public byte[] getDigest() {
        return digest;
    }

    @Override
    public void run() {
        DigestInputStream din = null;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            din = new DigestInputStream(new FileInputStream(this.input), sha);
            int b;
            while ((b = din.read()) != -1) ;
            din.close();
            this.digest = sha.digest();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
