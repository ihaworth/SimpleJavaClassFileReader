import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    public void the7thEntryInTheConstantPoolIsA_CONSTANT_Utf8_ConstantValue()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(7);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("ConstantValue", constantPoolEntry.getUtf8());
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
    public void the9thEntryInTheConstantPoolIsA_CONSTANT_Integer_WithValue10()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(9);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("LAST_FRAME", constantPoolEntry.getUtf8());
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
    public void the13thEntryInTheConstantPoolIsA_CONSTANT_Utf8_FrameArray()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(13);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("currentFrame", constantPoolEntry.getUtf8());
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
    public void the16thEntryInTheConstantPoolIsA_CONSTANT_Utf8_Code()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(16);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("Code", constantPoolEntry.getUtf8());
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
    public void the21stEntryInTheConstantPoolIsA_CONSTANT_Fieldref_ReferringToTheFramesFieldOfTheGameClass()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(21);
        assertEquals(ConstantPoolTagType.CONSTANT_Fieldref, constantPoolEntry.getType());
        assertEquals(1, constantPoolEntry.getClassIndex()); // Refers to bowling/Game (see above).
        assertEquals(22, constantPoolEntry.getNameAndTypeIndex()); // Refers to Frame[] frames
    }

    @Test
    public void the22ndEntryInTheConstantPoolIsA_CONSTANT_NameAndType_ReferringToTheFramesField()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(22);
        assertEquals(ConstantPoolTagType.CONSTANT_NameAndType, constantPoolEntry.getType());
        assertEquals(11, constantPoolEntry.getNameIndex()); // Refers to the 'frames' field...
        assertEquals(12, constantPoolEntry.getDescriptorIndex()); // ...of type bowling.Frame[]
    }

    @Test
    public void the23ndEntryInTheConstantPoolIsA_CONSTANT_Methodref_ReferringToTheFramesField()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(23);
        assertEquals(ConstantPoolTagType.CONSTANT_Methodref, constantPoolEntry.getType());
        assertEquals(1, constantPoolEntry.getClassIndex()); // Refers to bowling/Game (see above).
        assertEquals(24, constantPoolEntry.getNameAndTypeIndex()); // Refers to void initFrames() (See below)
    }

    @Test
    public void the24thEntryInTheConstantPoolIsA_CONSTANT_Utf8_Code()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(24);
        assertEquals(ConstantPoolTagType.CONSTANT_NameAndType, constantPoolEntry.getType());
        assertEquals(25, constantPoolEntry.getNameIndex()); // Refers to initFrames
        assertEquals(15, constantPoolEntry.getDescriptorIndex()); // Refers to ()V signature
    }

    @Test
    public void the25thEntryInTheConstantPoolIsA_CONSTANT_Utf8_Code()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(25);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("initFrames", constantPoolEntry.getUtf8());
    }

    @Test
    public void the26thEntryInTheConstantPoolIsA_CONSTANT_Utf8_LineNumberTable()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(26);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("LineNumberTable", constantPoolEntry.getUtf8());
    }

    @Test
    public void the27thEntryInTheConstantPoolIsA_CONSTANT_Utf8_LocalVariableTable()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(27);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("LocalVariableTable", constantPoolEntry.getUtf8());
    }

    @Test
    public void the39thEntryInTheConstantPoolIsA_CONSTANT_Utf8_LocalVariableTable()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(39);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("StackMapTable", constantPoolEntry.getUtf8());
    }

    @Test
    public void the40thEntryInTheConstantPoolIsA_CONSTANT_Utf8_LocalVariableTable()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(40);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("roll", constantPoolEntry.getUtf8());
    }

    @Test
    public void the41thEntryInTheConstantPoolIsA_CONSTANT_Utf8_LocalVariableTable()
    {
        constantPool = javaClassFileReader.getConstantPool();
        constantPoolEntry = constantPool.getEntry(41);
        assertEquals(ConstantPoolTagType.CONSTANT_Utf8, constantPoolEntry.getType());
        assertEquals("(I)V", constantPoolEntry.getUtf8());
    }

    @Test
    public void canReadAllConstantsInThePool()
    {
        constantPool = javaClassFileReader.getConstantPool();
        for (int i = 1; i < javaClassFileReader.getConstantPoolCount() - 1; i++)
        {
            constantPoolEntry = constantPool.getEntry(i);
            assertNotNull(constantPoolEntry);

            //            if (constantPoolEntry.getType() == ConstantPoolTagType.CONSTANT_Utf8)
            //                System.out.println(constantPoolEntry.getUtf8());
        }
    }

    @Test
    public void accessFlagsAre0x21()
    {
        // Refers to a Public class which treats the super class methods specially when invoked with invokespecial
        assertEquals(0x21, javaClassFileReader.getAccessFlags());
    }

    @Test
    public void thisClassIndexIs1()
    {
        assertEquals(1, javaClassFileReader.getThisClassIndex());   // 1 refers to bowling/Game
    }

    @Test
    public void superClassIs3()
    {
        assertEquals(3, javaClassFileReader.getSuperClassIndex());  // 3 refers to java/lang/Object
    }

    @Test
    public void interfacesCountIs0()
    {
        assertEquals(0, javaClassFileReader.getInterfacesCount());
    }

    @Test
    public void interfacesAreNotDefined()
    {
        // TODO Refactor to getInterface(index) to be consistent!
        assertArrayEquals(new int[0], javaClassFileReader.getInterfaces());
    }

    @Test
    public void fieldCountIs4()
    {
        assertEquals(4, javaClassFileReader.getFieldCount());
    }

    @Test
    public void firstFieldIs_NUM_FRAMES()
    {
        Field field = javaClassFileReader.getField(0);
        assertEquals(0x1A, field.getAccessFlags());     // 0x1A is private final static
        assertEquals(5, field.getNameIndex());          // Entry 5 in the constant pool is NUM_FRAMES
        assertEquals(6, field.getDescriptorIndex());    // Entry 6 in the constant pool is I

        assertEquals(1, field.getAttributesCount());

        Attribute attribute = field.getAttribute(0);
        assertEquals(7, attribute.getNameIndex());      // Entry 7 in the constant pool is ConstantValue
        assertEquals(2, attribute.getLength());         // ConstantValue attributes always have length 2.
    }

    @Test
    public void secondFieldIs_LAST_FRAME()
    {
        Field field = javaClassFileReader.getField(1);
        assertEquals(0x1A, field.getAccessFlags());     // 0x1A is private final static
        assertEquals(9, field.getNameIndex());          // Entry 9 in the constant pool is LAST_FRAME
        assertEquals(6, field.getDescriptorIndex());    // Entry 6 in the constant pool is I

        assertEquals(1, field.getAttributesCount());

        Attribute attribute = field.getAttribute(0);
        assertEquals(7, attribute.getNameIndex());      // Entry 7 in the constant pool is ConstantValue
        assertEquals(2, attribute.getLength());         // ConstantValue attributes always have length 2.
    }

    @Test
    public void thirdFieldIs_frames()
    {
        Field field = javaClassFileReader.getField(2);
        assertEquals(0x12, field.getAccessFlags());      // 0x12 is private final
        assertEquals(11, field.getNameIndex());          // Entry 11 in the constant pool is frames
        assertEquals(12, field.getDescriptorIndex());    // Entry 12 in the constant pool is [Lbowling/Frame; (i.e. Frame[])

        assertEquals(0, field.getAttributesCount());
    }

    @Test
    public void fourthFieldIs_currentFrame()
    {
        Field field = javaClassFileReader.getField(3);
        assertEquals(0x02, field.getAccessFlags());      // 0x02 is private
        assertEquals(13, field.getNameIndex());          // Entry 13 in the constant pool is currentFrame
        assertEquals(6, field.getDescriptorIndex());     // Entry 6 in the constant pool is [Lbowling/Frame; (i.e. Frame[])

        assertEquals(0, field.getAttributesCount());
    }

    @Test
    public void methodCountIs6()
    {
        assertEquals(6, javaClassFileReader.getMethodCount());
    }

    @Test
    public void firstMethodIs_init()
    {
        Method method = javaClassFileReader.getMethod(0);
        assertEquals(0x01, method.getAccessFlags());        // 0x01 is public
        assertEquals(14, method.getNameIndex());            // <init>
        assertEquals(15, method.getDescriptorIndex());      // ()V

        assertEquals(1, method.getAttributesCount());

        CodeAttribute codeAttribute = method.getAttribute(0);
        assertEquals(16, codeAttribute.getNameIndex());      // Code
        assertEquals(72, codeAttribute.getLength());
        assertEquals(2, codeAttribute.getMaxStack());
        assertEquals(1, codeAttribute.getMaxLocals());
        assertEquals(18, codeAttribute.getCodeLength());
        assertArrayEquals(new int[] {42,183,0,17,42,16,10,189,0,19,181,0,21,42,183,0,23,177}, codeAttribute.getCode());
        assertEquals(0, codeAttribute.getExceptionTableLength());
        //        assertArrayEquals(new ExceptionTableEntry[0], attribute.getExceptionTableEntry());
        assertEquals(2, codeAttribute.getAttributesCount());

        Attribute firstAttribute = codeAttribute.getAttribute(0);
        assertEquals(26, firstAttribute.getNameIndex());      // LineNumberTable
        assertEquals(18, firstAttribute.getLength());

        Attribute secondAttribute = codeAttribute.getAttribute(1);
        assertEquals(27, secondAttribute.getNameIndex());      // LocalVariableTable
        assertEquals(12, secondAttribute.getLength());
    }

    @Test
    public void secondMethodIs_initFrames()
    {
        Method method = javaClassFileReader.getMethod(1);
        assertEquals(0x02, method.getAccessFlags());        // 0x02 is private
        assertEquals(25, method.getNameIndex());            // initFrames
        assertEquals(15, method.getDescriptorIndex());      // ()V

        assertEquals(1, method.getAttributesCount());

        CodeAttribute codeAttribute = method.getAttribute(0);
        assertEquals(16, codeAttribute.getNameIndex());     // Code
        assertEquals(143, codeAttribute.getLength());
        assertEquals(4, codeAttribute.getMaxStack());
        assertEquals(2, codeAttribute.getMaxLocals());
        assertEquals(58, codeAttribute.getCodeLength());
        //        assertArrayEquals(new int[] {42,183,0,17,42,16,10,189,0,19,181,0,21,42,183,0,23,177}, codeAttribute.getCode());
        assertEquals(0, codeAttribute.getExceptionTableLength());
        //        assertArrayEquals(new ExceptionTableEntry[0], attribute.getExceptionTableEntry());
        assertEquals(3, codeAttribute.getAttributesCount());

        Attribute firstAttribute = codeAttribute.getAttribute(0);
        assertEquals(26, firstAttribute.getNameIndex());      // LineNumberTable
        assertEquals(26, firstAttribute.getLength());

        Attribute secondAttribute = codeAttribute.getAttribute(1);
        assertEquals(27, secondAttribute.getNameIndex());      // LocalVariableTable
        assertEquals(22, secondAttribute.getLength());

        Attribute thirdAttribute = codeAttribute.getAttribute(2);
        assertEquals(39, thirdAttribute.getNameIndex());      // StackMapTable
        assertEquals(7, thirdAttribute.getLength());
    }

    @Test
    public void thirdMethodIs_XXX()
    {
        Method method = javaClassFileReader.getMethod(2);
        assertEquals(0x01, method.getAccessFlags());        // 0x01 is public
        assertEquals(40, method.getNameIndex());            // roll
        assertEquals(41, method.getDescriptorIndex());      // ()V

        assertEquals(1, method.getAttributesCount());

        CodeAttribute codeAttribute = method.getAttribute(0);
        assertEquals(16, codeAttribute.getNameIndex());     // Code
        assertEquals(97, codeAttribute.getLength());
        assertEquals(2, codeAttribute.getMaxStack());
        assertEquals(2, codeAttribute.getMaxLocals());
        assertEquals(24, codeAttribute.getCodeLength());
        //        assertArrayEquals(new int[] {42,183,0,17,42,16,10,189,0,19,181,0,21,42,183,0,23,177}, codeAttribute.getCode());
        assertEquals(0, codeAttribute.getExceptionTableLength());
        //        assertArrayEquals(new ExceptionTableEntry[0], attribute.getExceptionTableEntry());
        assertEquals(3, codeAttribute.getAttributesCount());

        Attribute firstAttribute = codeAttribute.getAttribute(0);
        assertEquals(26, firstAttribute.getNameIndex());      // LineNumberTable
        assertEquals(18, firstAttribute.getLength());

        Attribute secondAttribute = codeAttribute.getAttribute(1);
        assertEquals(27, secondAttribute.getNameIndex());      // LocalVariableTable
        assertEquals(22, secondAttribute.getLength());

        Attribute thirdAttribute = codeAttribute.getAttribute(2);
        assertEquals(39, thirdAttribute.getNameIndex());      // StackMapTable
        assertEquals(3, thirdAttribute.getLength());
    }
}
