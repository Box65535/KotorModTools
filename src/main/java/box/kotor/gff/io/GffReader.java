package box.kotor.gff.io;

import box.kotor.io.KotorDataInput;

public class GffReader {
    
    private final int structOffset;
    private final int fieldOffset;
    private final int labelOffset;
    private final int fieldDataOffset;
    private final int fieldIndecesOffset;
    private final int listIndecesOffset;
    
    public GffReader(KotorDataInput in) {
        
        structOffset = in.readUnsignedInt();
        in.readUnsignedInt();
        fieldOffset = in.readUnsignedInt();
        in.readUnsignedInt();
        labelOffset = in.readUnsignedInt();
        in.readUnsignedInt();
        fieldDataOffset = in.readUnsignedInt();
        in.readUnsignedInt();
        fieldIndecesOffset = in.readUnsignedInt();
        in.readUnsignedInt();
        listIndecesOffset = in.readUnsignedInt();
        in.readUnsignedInt();
    }
    
    public int getStructOffset() {
        return structOffset;
    }
    
    public int getFieldOffset() {
        return fieldOffset;
    }
    
    public int getLabelOffset() {
        return labelOffset;
    }
    
    public int getFieldDataOffset() {
        return fieldDataOffset;
    }
    
    public int getFieldIndecesOffset() {
        return fieldIndecesOffset;
    }
    
    public int getListIndecesOffset() {
        return listIndecesOffset;
    }
}
