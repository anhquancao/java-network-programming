package tp3.ex2.up13.monftp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by caoquan on 1/31/17.
 */
public class FTPURLConnection extends URLConnection {

    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    protected FTPURLConnection(URL url) {
        super(url);
        setContentHandlerFactory(new FPTContentHandlerFactory());
    }

    @Override
    public void connect() throws IOException {
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }

    public String getHeaderField(String name) {
        if (name.equals("content -type")) return "monftp";
        return null;
    }
}
