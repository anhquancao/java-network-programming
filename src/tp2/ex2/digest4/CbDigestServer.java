package tp2.ex2.digest4;


import java.io.File;

/**
 * Created by caoquan on 1/24/17.
 */
public class CbDigestServer {

    public static void main(String[] args) {
        CbDigest[] cbDigestTab = new CbDigest[5];

        for (int i = 0; i < 5; i++) {
            File f = new File("/Users/caoquan/Desktop/5d6550f83cd22efdf437fca6760c9ee4a8893a43.png");
            cbDigestTab[i] = new CbDigest(f);
            cbDigestTab[i].start();
        }
        try {
            for (int i = 0; i < 5; i++) {
                cbDigestTab[i].join();
                byte[] digest = cbDigestTab[i].getDigest();
                String fileName = cbDigestTab[i].getFileName();
                System.out.print(fileName + ": ");
                if (digest != null) {
                    for (int j = 0; j < 5; j++) {
                        System.out.print(digest[j] + " ");
                    }
                } else {
                    System.out.print("unusable digest");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
