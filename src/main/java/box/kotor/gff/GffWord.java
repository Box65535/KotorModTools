package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public class GffWord extends GffField {
    
    private final short data;
    
    GffWord(GffReader reader, KotorDataInput in) {
        super(reader, in);
        data = in.readUnsignedShort();
        in.readUnsignedShort();
    }
    
    public short get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
