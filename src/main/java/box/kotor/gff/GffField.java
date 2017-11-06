package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

public abstract class GffField implements GffValue {
    
    private final String label;
    
    protected GffField() {
        label = null;
    }
    
    protected GffField(String label) {
        this.label = label;
    }
    
    protected GffField(GffReader reader, KotorDataInput in) {
        
        int index = in.readUnsignedInt();
        
        int pos = in.getPosition();
        in.setPosition(reader.getLabelOffset() + 16*index);
        this.label = in.readString(16);
        in.setPosition(pos);
    }
    
    @Override
    public String getLabel() {
        return label;
    }
}
