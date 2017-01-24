package tp2.ex2.digest2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by caoquan on 1/24/17.
 */
public class CbDigest implements Runnable {
    private File input;
    private Vector digestList = new Vector();

    public CbDigest(File input) {
        this.input = input;
    }

    public synchronized void addListener(DigestListener listener) {
        digestList.add(listener);
    }

    public synchronized void removeListener(DigestListener listener) {
        digestList.remove(listener);
    }

    private synchronized void sendDigest(byte[] digest) {
        ListIterator iterator = digestList.listIterator();
        while (iterator.hasNext()) {
            DigestListener dl = (DigestListener) iterator.next();
            dl.setDigest(digest);
        }
    }

    @Override
    public void run() {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            DigestInputStream din = new DigestInputStream(new FileInputStream(this.input), sha);
            while (din.read() != -1) ;
            din.close();
            byte[] digest = sha.digest();
            sendDigest(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
