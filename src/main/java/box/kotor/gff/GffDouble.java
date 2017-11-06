package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public class GffDouble extends GffField {
    
    private final double data;
    
    GffDouble(GffReader reader, KotorDataInput in) {
        super(reader, in);
        data = 0;
        in.readUnsignedInt();
    }
    
    public double get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
