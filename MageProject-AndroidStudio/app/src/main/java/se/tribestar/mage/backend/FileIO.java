package se.tribestar.mage.backend;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface for file input/output.
 * Created by Andreas Stjerndal on 03-Jan-2015.
 *
 * * from the book "Beginning Android Games", by Mario Zechner
 */
public interface FileIO {

    public InputStream readFile(String fileName) throws IOException;

    public OutputStream writeFile(String fileName) throws IOException;

    public InputStream readAsset(String fileName) throws IOException;
}
