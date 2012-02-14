import java.io.IOException;
import java.io.InputStream;

public class ByteReadingHelper
{
    protected final InputStream inputStream;

    public ByteReadingHelper(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    protected int readU1() throws IOException
    {
        return inputStream.read();
    }

    protected int readU2() throws IOException
    {
        byte [] byteArray = new byte[2];
        inputStream.read(byteArray, 0, 2);

        return (((int)byteArray[0]) << 8) | ((int)byteArray[1]);
    }
}
