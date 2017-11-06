package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public class GffByte extends GffField {
    
    private final byte data;
    
    public GffByte(String label, byte data) {
        super(label);
        this.data = data;
    }
    
    GffByte(GffReader reader, KotorDataInput in) {
        super(reader, in);
        
        this.data = in.readByte();
        in.readByte();
        in.readByte();
        in.readByte();
    }
    
    public byte get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
