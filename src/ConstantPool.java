import java.io.IOException;
import java.io.InputStream;


public class ConstantPool extends ByteReadingHelper
{
    private final int constantPoolCount;

    private final ConstantPoolEntry constantPoolEntries[];

    public ConstantPool(InputStream inputStream, int constantPoolCount)
    {
        super(inputStream);
        this.constantPoolCount = constantPoolCount;
        constantPoolEntries = new ConstantPoolEntry[constantPoolCount];
    }

    public void read() throws IOException
    {
        for (int i = 1; i < constantPoolCount - 1; i++)
        {
            int constantPoolTagNumber = readU1();

            if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Class.getTagValue())
            {
                int nameIndex = readU2();
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Class, nameIndex);
            }
        }
    }

    public ConstantPoolEntry getEntry(int i)
    {
        return constantPoolEntries[i];
    }
}
