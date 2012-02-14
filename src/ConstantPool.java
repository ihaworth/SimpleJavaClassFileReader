import java.io.DataInputStream;
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
        for (int i = 1; i < (constantPoolCount); i++)
        {
            int constantPoolTagNumber = readU1();

            if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Class.getTagValue())
            {
                int nameIndex = readU2();
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Class);
                constantPoolEntries[i].setNameIndex(nameIndex);
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Utf8.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Utf8);
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                constantPoolEntries[i].setUtf8(dataInputStream.readUTF());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Integer.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Integer);
                // TODO Consider doing all access via the data input stream?
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                constantPoolEntries[i].setInteger(dataInputStream.readInt());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Methodref.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Methodref);
                constantPoolEntries[i].setClassIndex(readU2());
                constantPoolEntries[i].setNameAndTypeIndex(readU2());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_NameAndType.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_NameAndType);
                constantPoolEntries[i].setNameIndex(readU2());
                constantPoolEntries[i].setDescriptorIndex(readU2());
            }
            else
            {
                System.err.println("Failed to read constant pool item of tag type: " + constantPoolTagNumber + "\tat index: " + i);
            }
        }
    }

    public ConstantPoolEntry getEntry(int i)
    {
        return constantPoolEntries[i];
    }
}
