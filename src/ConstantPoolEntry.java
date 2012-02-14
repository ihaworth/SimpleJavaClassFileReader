
public class ConstantPoolEntry
{
    private final ConstantPoolTagType constantPoolTagType;
    private int nameIndex;
    private String utf8;
    private int integer;
    private int classIndex;
    private int nameAndTypeIndex;
    private Object descriptorIndex;

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
}
