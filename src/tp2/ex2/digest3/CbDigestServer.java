package tp2.ex2.digest3;

import java.io.File;

/**
 * Created by caoquan on 1/24/17.
 */
public class CbDigestServer {
    private File input;
    private byte[] digest;

    public CbDigestServer(File input) {
        this.input = input;
    }

    public void calculateDigest() {
        synchronized (this) {
            System.out.println("server");
            CbDigest digest = new CbDigest(this.input, this);
            Thread t = new Thread(digest);
            t.start();
            try {
                System.out.println("wait");
                wait();
            } catch (InterruptedException e) {
            }

            System.out.println(this);
        }
    }

    public String toString() {
        String result = input.getName() + ": ";
        if (digest != null) {
            for (int i = 0; i < digest.length; i++) {
                result += digest[i] + " ";
            }
        } else {
            result += "unusable digest";
        }
        return result;
    }

    public void setDigest(byte[] digest) {
        this.digest = digest;
    }

    public static void main(String[] args) {
        File f = new File("/Users/caoquan/Desktop/5d6550f83cd22efdf437fca6760c9ee4a8893a43.png");
        CbDigestServer server = new CbDigestServer(f);
        server.calculateDigest();
    }
}
