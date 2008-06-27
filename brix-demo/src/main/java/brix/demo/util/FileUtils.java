package brix.demo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.wicket.util.io.Streams;

/**
 * Various file utilities
 * 
 * @author igor.vaynberg
 * 
 */
public class FileUtils
{
    /**
     * Constructor
     */
    private FileUtils()
    {

    }

    /**
     * {@link File#mkdirs()} that throws runtime exception if it fails
     * 
     * @param file
     */
    public static void mkdirs(File file)
    {
        if (!file.exists())
        {
            if (!file.mkdirs())
            {
                throw new RuntimeException("Could not create directory: " + file.getAbsolutePath());
            }
        }
    }

    public static void copyClassResourceToFile(String source, File destination)
    {
        final InputStream in = FileUtils.class.getClassLoader().getResourceAsStream("source");
        if (in == null)
        {
            throw new RuntimeException("Class resource: " + source + " does not exist");
        }

        try
        {
            final FileOutputStream fos = new FileOutputStream(destination);
            Streams.copy(in, fos);
            fos.close();
            in.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Could not copy class resource: " + source +
                " to destination: " + destination.getAbsolutePath());
        }
    }
}