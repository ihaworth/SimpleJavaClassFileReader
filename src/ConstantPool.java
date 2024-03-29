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

            // TODO Refactor to be more OO and reduce duplication.
            // TODO Refactor and get the ConstantPoolEntry to read itself?  This would remove the setters.

            if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Class.getTagValue())
            {
                int nameIndex = readU2();
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Class);
                constantPoolEntries[i].setNameIndex(nameIndex);
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Fieldref.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Fieldref);
                constantPoolEntries[i].setClassIndex(readU2());
                constantPoolEntries[i].setNameAndTypeIndex(readU2());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Methodref.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Methodref);
                constantPoolEntries[i].setClassIndex(readU2());
                constantPoolEntries[i].setNameAndTypeIndex(readU2());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_InterfaceMethodref.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_InterfaceMethodref);
                constantPoolEntries[i].setClassIndex(readU2());
                constantPoolEntries[i].setNameAndTypeIndex(readU2());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_String.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_String);
                constantPoolEntries[i].setStringIndex(readU2());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Integer.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Integer);
                // TODO Consider doing all access via the data input stream?
                constantPoolEntries[i].setInteger(new DataInputStream(inputStream).readInt());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Float.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Float);
                // TODO Consider doing all access via the data input stream?
                constantPoolEntries[i].setFloat(new DataInputStream(inputStream).readFloat());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Long.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Long);
                // TODO Consider doing all access via the data input stream?
                constantPoolEntries[i].setLong(new DataInputStream(inputStream).readLong());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_NameAndType.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_NameAndType);
                constantPoolEntries[i].setNameIndex(readU2());
                constantPoolEntries[i].setDescriptorIndex(readU2());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Double.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Float);
                // TODO Consider doing all access via the data input stream?
                constantPoolEntries[i].setDouble(new DataInputStream(inputStream).readDouble());
            }
            else if (constantPoolTagNumber == ConstantPoolTagType.CONSTANT_Utf8.getTagValue())
            {
                constantPoolEntries[i] = new ConstantPoolEntry(ConstantPoolTagType.CONSTANT_Utf8);
                // TODO Consider doing all access via the data input stream?
                constantPoolEntries[i].setUtf8(new DataInputStream(inputStream).readUTF());
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
