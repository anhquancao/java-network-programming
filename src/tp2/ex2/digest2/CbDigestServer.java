package tp2.ex2.digest2;

import java.io.File;

/**
 * Created by caoquan on 1/24/17.
 */
public class CbDigestServer implements DigestListener {

    private File input;
    private byte[] digest;

    public CbDigestServer(File in) {
        this.input = in;
    }

    public void calculateDigest() {
        CbDigest task = new CbDigest(this.input);
        task.addListener(this);
        Thread t = new Thread();
        t.start();
    }

    @Override
    public void setDigest(byte[] digest) {
        this.digest = digest;
    }

    public static void main(String args[]) {
        File f = new File("/Users/caoquan/Desktop/5d6550f83cd22efdf437fca6760c9ee4a8893a43.png");
        tp2.ex2.digest1.CbDigestServer server = new tp2.ex2.digest1.CbDigestServer(f);
        server.calculateDigest();
    }
}
