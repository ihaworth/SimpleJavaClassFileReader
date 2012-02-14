import java.io.IOException;
import java.io.InputStream;


public class JavaClassFileReader
{
    private static final int MAGIC_NUMBER_LENGTH = 4;

    private final InputStream inputStream;

    private byte[] magicNumbers;
    private int minorVersion;
    private int majorVersion;

    private Object constantPoolSize;

    public JavaClassFileReader(InputStream inputStream) throws IOException
    {
        this.inputStream = inputStream;
        readMagicNumber();
        readMinorVersion();
        readMajorVersion();
        readConstantPoolSize();
    }

    private void readMagicNumber() throws IOException
    {
        magicNumbers = new byte[MAGIC_NUMBER_LENGTH];
        inputStream.read(magicNumbers, 0, MAGIC_NUMBER_LENGTH);
    }

    private void readMinorVersion() throws IOException
    {
        minorVersion = readU2();
    }

    private void readMajorVersion() throws IOException
    {
        majorVersion = readU2();
    }

    private void readConstantPoolSize() throws IOException
    {
        constantPoolSize = readU2();
    }

    private int readU2() throws IOException
    {
        byte [] byteArray = new byte[2];
        inputStream.read(byteArray, 0, 2);

        return (((int)byteArray[0]) << 8) | ((int)byteArray[1]);
    }

    public byte[] getMagicNumber()
    {
        return magicNumbers;
    }

    public int getMinorVersion()
    {
        return minorVersion;
    }

    public int getMajorVersion()
    {
        return majorVersion;
    }

    public Object getConstantPoolCount()
    {
        return constantPoolSize;
    }
}
