package tp3;

import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by caoquan on 1/24/17.
 */
public class DigestRunnable implements Runnable {

    private InputStream in;
    private File file;

    public DigestRunnable(InputStream in, String filename) {
        this.in = in;
        this.file = new File(filename);
    }

    @Override
    public void run() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            DigestInputStream din = new DigestInputStream(new FileInputStream(this.file), sha);
            while (din.read() != -1) ;
            din.close();
            byte[] digest = sha.digest();

            String result = this.file.getName() + ": ";
            if (digest != null) {
                for (int i = 0; i < digest.length; i++) {
                    result += digest[i] + " ";
                }
            } else {
                result += "unusable digest";
            }
            System.out.println(result);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
