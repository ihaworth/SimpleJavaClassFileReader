import java.io.IOException;
import java.io.InputStream;

public class ExceptionTableEntry extends ByteReadingHelper
{
    private int startPc;
    private int endPc;
    private int handlerPc;
    private int catchType;

    public ExceptionTableEntry(InputStream inputStream) throws IOException
    {
        super(inputStream);

        readStartPc();
        readEndPc();
        readHandlerPc();
        readCatchType();
    }

    private void readStartPc() throws IOException
    {
        startPc = readU2();
    }

    private void readEndPc() throws IOException
    {
        endPc = readU2();
    }

    private void readHandlerPc() throws IOException
    {
        handlerPc = readU2();
    }

    private void readCatchType() throws IOException
    {
        catchType = readU2();
    }

    public int getStartPc()
    {
        return startPc;
    }

    public int getEndPc()
    {
        return endPc;
    }

    public int getHandlerPc()
    {
        return handlerPc;
    }

    public int getCatchType()
    {
        return catchType;
    }
}
