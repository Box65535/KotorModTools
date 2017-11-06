package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public class GffDWord extends GffField {
    
    private final int data;
    
    GffDWord(GffReader reader, KotorDataInput in) {
        super(reader, in);
        data = in.readUnsignedInt();
    }
    
    public int get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
