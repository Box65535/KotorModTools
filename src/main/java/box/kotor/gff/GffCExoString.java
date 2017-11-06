package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public class GffCExoString extends GffField {
    
    private final String data;
    
    GffCExoString(KotorDataInput in) {
        super();
        data = in.readString(in.readUnsignedInt());
    }
    
    GffCExoString(GffReader reader, KotorDataInput in) {
        super(reader, in);
        
        int offset = reader.getFieldDataOffset() + in.readUnsignedInt();
        int pos = in.getPosition();
        
        in.setPosition(offset);
        data = in.readString(in.readUnsignedInt());
        
        in.setPosition(pos);
    }
    
    public String get() {
        return data;
    }
    
    @Override
    public String toString() {
        return getLabel() + ": " + data;
    }
}
