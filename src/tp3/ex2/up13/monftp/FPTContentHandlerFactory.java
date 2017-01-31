package tp3.ex2.up13.monftp;

import java.net.ContentHandler;
import java.net.ContentHandlerFactory;

/**
 * Created by caoquan on 1/31/17.
 */
public class FPTContentHandlerFactory implements ContentHandlerFactory {
    @Override
    public ContentHandler createContentHandler(String mimetype) {
        return new FPTContentHandler();
    }
}
