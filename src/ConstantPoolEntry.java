
public class ConstantPoolEntry
{
    private final ConstantPoolTagType constantPoolTagType;
    private final int nameIndex;

    public ConstantPoolEntry(ConstantPoolTagType constantClass, int nameIndex)
    {
        this.constantPoolTagType = constantClass;
        this.nameIndex = nameIndex;
    }

    public ConstantPoolTagType getType()
    {
        return constantPoolTagType;
    }

    public int getNameIndex()
    {
        return nameIndex;
    }
}
