package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorDataOutput;

public class GffDWord64 extends GffField {
    
    private final long data;
    
    GffDWord64(GffReader reader, KotorDataInput in) {
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
