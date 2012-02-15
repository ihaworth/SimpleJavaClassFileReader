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
    public void theFifthEntryInTheConstantPoolIsA_CONSTANT_Utf8_NUM_FRAMES()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(5);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("NUM_FRAMES", constantPoolEntry.getUtf8());
    }

    @Test
    public void theSixthEntryInTheConstantPoolIsA_CONSTANT_Utf8_I()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(6);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("I", constantPoolEntry.getUtf8());
    }

    @Test
    public void the8thEntryInTheConstantPoolIsA_CONSTANT_Integer_WithValue10()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(8);
        assertEquals(ConstantPoolTagType.CONSTANT_Integer, constantPoolEntry.getType());
        assertEquals(10, constantPoolEntry.getInteger());
    }

    @Test
    public void the11thEntryInTheConstantPoolIsA_CONSTANT_Utf8_frames()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(11);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("frames", constantPoolEntry.getUtf8());
    }

    @Test
    public void the12thEntryInTheConstantPoolIsA_CONSTANT_Utf8_FrameArray()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(12);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("[Lbowling/Frame;", constantPoolEntry.getUtf8());
    }

    @Test
    public void the14thEntryInTheConstantPoolIsA_CONSTANT_Utf8_init()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(14);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("<init>", constantPoolEntry.getUtf8());
    }

    @Test
    public void the15thEntryInTheConstantPoolIsA_CONSTANT_Utf8_VoidMethodSignature()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(15);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("()V", constantPoolEntry.getUtf8());
    }

    @Test
    public void the17thEntryInTheConstantPoolIsA_CONSTANT_Methodref()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(17);
        assertEquals(ConstantPoolTagType.CONSTANT_Methodref, constantPoolEntry.getType());
        assertEquals(3, constantPoolEntry.getClassIndex()); // Refers to Object (see above).
        assertEquals(18, constantPoolEntry.getNameAndTypeIndex()); // Refers to <init> (See below)
    }

    @Test
    public void the18thEntryInTheConstantPoolIsA_CONSTANT_NameAndType_WithValueInitWithVoidSignature()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(18);
        assertEquals(ConstantPoolTagType.CONSTANT_NameAndType, constantPoolEntry.getType());
        assertEquals(14, constantPoolEntry.getNameIndex()); // Refers to <init>
        assertEquals(15, constantPoolEntry.getDescriptorIndex()); // Refers to ()V signature
    }

    @Test
    public void the21stEntryInTheConstantPoolIsA_CONSTANT_Fieldref_WithValueXXX()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(21);
        assertEquals(ConstantPoolTagType.CONSTANT_Fieldref, constantPoolEntry.getType());
        assertEquals(1, constantPoolEntry.getClassIndex()); // Refers to bowling/Game (see above).
        assertEquals(22, constantPoolEntry.getNameAndTypeIndex()); // Refers to Frame[] frames
    }

    @Test
    public void the22ndEntryInTheConstantPoolIsA_CONSTANT_NameAndType_WithValueXXX()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(22);
        assertEquals(ConstantPoolTagType.CONSTANT_NameAndType, constantPoolEntry.getType());
        assertEquals(11, constantPoolEntry.getNameIndex()); // Refers to the 'frames' field...
        assertEquals(12, constantPoolEntry.getDescriptorIndex()); // ...of type bowling.Frame[]
    }

    @Test
    public void canReadAllConstantsInThePool()
    {
        constantPool = javaClassFileReader.getConstantPool();
        for (int i = 1; i < javaClassFileReader.getConstantPoolCount() - 1; i++)
        {
            constantPoolEntry = constantPool.getEntry(i);
            //
            //            if (constantPoolEntry.getType() == ConstantPoolTagType.CONSTANT_Utf8)
            //            {
            //                System.out.println(i + " " + constantPoolEntry.getUtf8());
            //            }
        }
    }

    //    @Test
    //    public void accessFlagsAreXXX()
    //    {
    //        assertEquals(8972 /*0x230C*/, javaClassFileReader.getAccessFlags());
    //    }

}
