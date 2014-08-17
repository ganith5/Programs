package com.pavs.io;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import static junit.framework.Assert.assertEquals;

/**
 * Created by psachidananda on 8/14/14.
 */
public class RandomAccessFileTest {

    @Test
    public void testRandomFileAccess() {
        File inputFile = new File("src/main/java/com/pavs/io/input.txt");
        byte[] b = new byte[1024];

        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(inputFile, "rw");
            randomAccessFile.seek(10);
            randomAccessFile.read(b, (int) randomAccessFile.getFilePointer(), 5);
            String fileReadStr = new String(b);

            assertEquals("test", fileReadStr.trim()); //requires a trim here because byte array has empty space



            randomAccessFile.close();
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}
