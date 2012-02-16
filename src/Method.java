import java.io.IOException;
import java.io.InputStream;


public class Method extends ByteReadingHelper
{
    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCount;
    private CodeAttribute[] attributes;

    public Method(InputStream inputStream) throws IOException
    {
        super(inputStream);

        readAccessFlags();
        readNameIndex();
        readDescriptorIndex();
        readAttributesCount();
        readAttributes();
    }

    private void readAccessFlags() throws IOException
    {
        accessFlags = readU2();
    }

    private void readNameIndex() throws IOException
    {
        nameIndex = readU2();
    }

    private void readDescriptorIndex() throws IOException
    {
        descriptorIndex = readU2();
    }

    private void readAttributesCount() throws IOException
    {
        attributesCount = readU2();
    }

    private void readAttributes() throws IOException
    {
        attributes = new CodeAttribute[attributesCount];

        for (int i = 0; i < attributesCount; i++)
        {
            attributes[i] = new CodeAttribute(inputStream);
        }
    }

    public int getAccessFlags()
    {
        return accessFlags;
    }

    public int getNameIndex()
    {
        return nameIndex;
    }

    public int getDescriptorIndex()
    {
        return descriptorIndex;
    }

    public int getAttributesCount()
    {
        return attributesCount;
    }

    public CodeAttribute getAttribute(int i)
    {
        return attributes[i];
    }
}
