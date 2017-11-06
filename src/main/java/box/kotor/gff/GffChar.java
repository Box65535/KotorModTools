package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public class GffChar extends GffField {
    
    private final char data;
    
    public GffChar(String label, char data) {
        super(label);
        this.data = data;
    }
    
    GffChar(GffReader reader, KotorDataInput in) {
        super(reader, in);
        
        this.data = (char)in.readByte();
        in.readByte();
        in.readByte();
        in.readByte();
    }
    
    public char get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
