package dhz.rscarchiver;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Util
{
	public static byte[] getFileBytes(File file) throws IOException
	{
		return getFileBytes(file.getAbsolutePath());
	}
	
	public static byte[] getFileBytes(String filename) throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(filename, "r");
		byte[] buffer = new byte[(int) raf.length()];
		raf.read(buffer);
		raf.close();
		return buffer;
	}
}
