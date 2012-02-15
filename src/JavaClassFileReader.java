import java.io.IOException;
import java.io.InputStream;


public class JavaClassFileReader extends ByteReadingHelper
{
    private static final int MAGIC_NUMBER_LENGTH = 4;

    private byte[] magicNumbers;
    private int minorVersion;
    private int majorVersion;

    private int constantPoolCount;
    private ConstantPool constantPool;

    private int accessFlags;

    private int thisClassIndex;
    private int superClassIndex;

    private int interfacesCount;

    public JavaClassFileReader(InputStream inputStream) throws IOException
    {
        super(inputStream);

        readJavaClassFile();
    }

    private void readJavaClassFile() throws IOException
    {
        readMagicNumber();
        readMinorVersion();
        readMajorVersion();
        readConstantPoolCount();
        readConstantPool();
        readAccessFlags();
        readThisClassIndex();
        readSuperClassIndex();
        readInterfacesCount();
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

    private void readConstantPoolCount() throws IOException
    {
        constantPoolCount = readU2();
    }

    private void readConstantPool() throws IOException
    {
        constantPool = new ConstantPool(inputStream, constantPoolCount);
        constantPool.read();
    }

    private void readAccessFlags() throws IOException
    {
        accessFlags = readU2();
    }

    private void readThisClassIndex() throws IOException
    {
        thisClassIndex = readU2();
    }

    private void readSuperClassIndex() throws IOException
    {
        superClassIndex = readU2();
    }

    private void readInterfacesCount() throws IOException
    {
        interfacesCount = readU2();
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

    public int getConstantPoolCount()
    {
        return constantPoolCount;
    }

    public ConstantPool getConstantPool()
    {
        return constantPool;
    }

    public int getAccessFlags()
    {
        return accessFlags;
    }

    public int getThisClassIndex()
    {
        return thisClassIndex;
    }

    public int getSuperClassIndex()
    {
        return superClassIndex;
    }

    public int getInterfacesCount()
    {
        return interfacesCount;
    }
}
