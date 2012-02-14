
public class ConstantPoolEntry
{
    private final ConstantPoolTagType constantPoolTagType;
    private int nameIndex;
    private String utf8;

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
}
