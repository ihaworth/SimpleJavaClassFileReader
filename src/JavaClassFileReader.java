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
    private int[] interfaces;

    private int fieldCount;
    private Field[] fields;

    private int methodCount;
    private Method[] methods;

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
        readInterfaces();
        readFieldCount();
        readFields();
        readMethodCount();
        readMethods();
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

    private void readInterfaces() throws IOException
    {
        interfaces = new int[interfacesCount];

        for (int i = 0; i < interfacesCount; i++)
        {
            interfaces[i] = readU2();
        }
    }

    private void readFieldCount() throws IOException
    {
        fieldCount = readU2();
    }

    private void readFields() throws IOException
    {
        fields = new Field[fieldCount];

        for (int i = 0; i < fieldCount; i++)
        {
            fields[i] = new Field(inputStream);
        }
    }

    private void readMethodCount() throws IOException
    {
        methodCount = readU2();
    }

    private void readMethods() throws IOException
    {
        methods = new Method[methodCount];

        for (int i = 0; i < methodCount; i++)
        {
            methods[i] = new Method(inputStream);
        }
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

    public int[] getInterfaces()
    {
        return interfaces;
    }

    public int getFieldCount()
    {
        return fieldCount;
    }

    public Field getField(int i)
    {
        return fields[i];
    }

    public int getMethodCount()
    {
        return methodCount;
    }

    public Method getMethod(int i)
    {
        return methods[i];
    }
}
