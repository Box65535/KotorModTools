package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public class GffInt64 extends GffField {
    
    private final long data;
    
    GffInt64(GffReader reader, KotorDataInput in) {
        super(reader, in);
        data = 0;
        in.readUnsignedInt();
    }
    
    public long get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
