import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

public class JavaClassFileReaderTest
{
    private JavaClassFileReader javaClassFileReader;

    @Before
    public void setUp() throws IOException
    {
        InputStream resourceAsStream = getClass().getResourceAsStream("Game_class");
        javaClassFileReader = new JavaClassFileReader(resourceAsStream);
    }

    @Test
    public void magicNumberIdentifiesAClassFile()
    {
        byte[] expected = {(byte)0xCA, (byte)0xFE, (byte)0xBA, (byte)0xBE};
        assertArrayEquals(expected, javaClassFileReader.getMagicNumber());
    }

    @Test
    public void minorVersionIs0x0000()
    {
        assertEquals(0x0000, javaClassFileReader.getMinorVersion());
    }

    @Test
    public void majorVersionIs0x0032()
    {
        assertEquals(0x0032, javaClassFileReader.getMajorVersion());
    }

    @Test
    public void constantPoolCountIs0x0043()
    {
        assertEquals(0x0043, javaClassFileReader.getConstantPoolCount());
    }

    @Test
    public void theFirstEntryInTheConstantPoolIsA_CONSTANT_Class()
    {
        ConstantPool constantPool = javaClassFileReader.getConstantPool();
        ConstantPoolEntry constantPoolEntry = constantPool.getEntry(1);
        assertEquals(ConstantPoolTagType.CONSTANT_Class, constantPoolEntry.getType());
    }
}
