import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CodeAttribute extends Attribute
{
    private int maxStack;
    private int maxLocals;
    private int codeLength;
    private int[] code;
    private int exceptionTableLength;
    private ExceptionTableEntry[] exceptionTable;
    private int attributesCount;
    private Attribute[] attributes;

    public CodeAttribute(InputStream inputStream) throws IOException
    {
        super(inputStream);
    }

    @Override
    protected void readSpecificFields() throws IOException
    {
        readMaxStack();
        readMaxLocals();
        readCodeLength();
        readCode();
        readExceptionTableLength();
        readExceptionTable();
        readAttributesCount();
        readAttributes();
    }

    private void readMaxStack() throws IOException
    {
        maxStack = readU2();
    }

    private void readMaxLocals() throws IOException
    {
        maxLocals = readU2();
    }

    private void readCodeLength() throws IOException
    {
        codeLength = new DataInputStream(inputStream).readInt();
    }

    private void readCode() throws IOException
    {
        code = new int[codeLength];

        for (int i = 0; i < codeLength; i++)
        {
            code[i] = inputStream.read();
        }
    }

    private void readExceptionTableLength() throws IOException
    {
        exceptionTableLength = readU2();
    }

    private void readExceptionTable() throws IOException
    {
        exceptionTable = new ExceptionTableEntry[exceptionTableLength];

        for (int i = 0; i < exceptionTableLength; i++)
        {
            exceptionTable[i] = new ExceptionTableEntry(inputStream);
        }
    }

    private void readAttributesCount() throws IOException
    {
        attributesCount = readU2();
    }

    private void readAttributes() throws IOException
    {
        attributes = new Attribute[attributesCount];

        for (int i = 0; i < attributesCount; i++)
        {
            attributes[i] = new Attribute(inputStream);
        }
    }

    public int getMaxStack()
    {
        return maxStack;
    }

    public int getMaxLocals()
    {
        return maxLocals;
    }

    public int getCodeLength()
    {
        return codeLength;
    }

    public int[] getCode()
    {
        return code;
    }

    public int getExceptionTableLength()
    {
        return exceptionTableLength;
    }

    public ExceptionTableEntry getExceptionTableEntry(int i)
    {
        return exceptionTable[i];
    }

    public int getAttributesCount()
    {
        return attributesCount;
    }

    public Attribute getAttribute(int i)
    {
        return attributes[i];
    }
}
