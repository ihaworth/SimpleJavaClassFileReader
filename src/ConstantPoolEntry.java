
public class ConstantPoolEntry
{
    private final ConstantPoolTagType constantPoolTagType;
    private int nameIndex;
    private String utf8;
    private int integer;
    private int classIndex;
    private int nameAndTypeIndex;
    private Object descriptorIndex;
    private int stringIndex;
    private float floatValue;
    private long longValue;
    private double doubleValue;

    public ConstantPoolEntry(ConstantPoolTagType constantClass)
    {
        this.constantPoolTagType = constantClass;
    }

    public ConstantPoolTagType getType()
    {
        return constantPoolTagType;
    }

    public int getNameIndex()
    {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex)
    {
        this.nameIndex = nameIndex;
    }

    public void setUtf8(String utf8)
    {
        this.utf8 = utf8;
    }

    public String getUtf8()
    {
        return utf8;
    }

    public void setInteger(int integer)
    {
        this.integer = integer;
    }

    public int getInteger()
    {
        return integer;
    }

    public void setClassIndex(int classIndex)
    {
        this.classIndex = classIndex;
    }

    public int getClassIndex()
    {
        return classIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex)
    {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getNameAndTypeIndex()
    {
        return nameAndTypeIndex;
    }

    public void setDescriptorIndex(Object descriptorIndex)
    {
        this.descriptorIndex = descriptorIndex;
    }

    public Object getDescriptorIndex()
    {
        return descriptorIndex;
    }

    public void setStringIndex(int stringIndex)
    {
        this.stringIndex = stringIndex;
    }

    public int getStringIndex()
    {
        return stringIndex;
    }

    public void setFloat(float floatValue)
    {
        this.floatValue = floatValue;
    }

    public float getFloat()
    {
        return floatValue;
    }

    public void setLong(long longValue)
    {
        this.longValue = longValue;
    }

    public long getLong()
    {
        return longValue;
    }

    public void setDouble(double doubleValue)
    {
        this.doubleValue = doubleValue;
    }

    public double getDouble()
    {
        return doubleValue;
    }
}
