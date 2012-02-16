import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Attribute extends ByteReadingHelper
{
    private int nameIndex;
    private int length;

    public Attribute(InputStream inputStream) throws IOException
    {
        super(inputStream);

        readNameIndex();
        readLength();
        readSpecificFields();
    }

    protected void readSpecificFields() throws IOException
    {
        // TODO Read the specific data for each type instead of just chewing through the bytes.
        readRemainingBytes();
    }

    private void readNameIndex() throws IOException
    {
        nameIndex = readU2();
    }

    private void readLength() throws IOException
    {
        length = new DataInputStream(inputStream).readInt();
    }

    private void readRemainingBytes() throws IOException
    {
        for (int i = 0; i < length; i++)
        {
            inputStream.read();
        }
    }

    public int getNameIndex()
    {
        return nameIndex;
    }

    public int getLength()
    {
        return length;
    }
}
