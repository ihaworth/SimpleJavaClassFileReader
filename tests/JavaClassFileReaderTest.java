import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

public class JavaClassFileReaderTest
{
    private JavaClassFileReader javaClassFileReader;
    private ConstantPool constantPool;
    private ConstantPoolEntry constantPoolEntry;

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
    public void theFirstEntryInTheConstantPoolIsA_CONSTANT_Class_WithNameIndex0x002()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(1);
        assertEquals(ConstantPoolTagType.CONSTANT_Class, constantPoolEntry.getType());
        assertEquals(0x0002, constantPoolEntry.getNameIndex());
    }

    @Test
    public void theSecondEntryInTheConstantPoolIsA_CONSTANT_Utf8_forTheBowlingGameClass()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(2);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("bowling/Game", constantPoolEntry.getUtf8());
    }

    @Test
    public void theThirdEntryInTheConstantPoolIsA_CONSTANT_Class_WithNameIndex0x004()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(3);
        assertEquals(ConstantPoolTagType.CONSTANT_Class, constantPoolEntry.getType());
        assertEquals(0x0004, constantPoolEntry.getNameIndex());
    }

    @Test
    public void theFourthEntryInTheConstantPoolIsA_CONSTANT_Utf8_forTheBowlingGameClass()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(4);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("java/lang/Object", constantPoolEntry.getUtf8());
    }

    @Test
    public void theFifthEntryInTheConstantPoolIsA_CONSTANT_Utf8_WithNameIndex0x004()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(5);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("NUM_FRAMES", constantPoolEntry.getUtf8());
    }

    @Test
    public void theSixthEntryInTheConstantPoolIsA_CONSTANT_Utf8_WithNameIndex0x004()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(6);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("I", constantPoolEntry.getUtf8());
    }

    @Test
    public void canReadAllConstantsInThePool()
    {
        constantPool = javaClassFileReader.getConstantPool();
        for (int i = 1; i < javaClassFileReader.getConstantPoolCount() - 1; i++)
        {
            constantPoolEntry = constantPool.getEntry(i);
        }
    }
}
