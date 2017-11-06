package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorDataOutput;

public class GffFloat extends GffField {
    
    private final float data;
    
    GffFloat(GffReader reader, KotorDataInput in) {
        super(reader, in);
        data = in.readFloat();
    }
    
    public float get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
