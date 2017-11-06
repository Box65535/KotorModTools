package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public class GffVoid extends GffField {
    
    private final byte[] data = new byte[]{};
    
    GffVoid(GffReader reader, KotorDataInput in) {
        super(reader, in);
        in.readUnsignedInt();
    }
    
    public byte[] get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
