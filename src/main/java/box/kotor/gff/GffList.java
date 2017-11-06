package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GffList implements GffValue, Iterable<GffStruct> {
    
    private final String label;
    private final List<GffStruct> data = new ArrayList<>();
    
    public GffList(String label) {
        this.label = label;
    }
    
    GffList(GffReader reader, KotorDataInput in) {
    
        int labelIndex = in.readUnsignedInt();
    
        int pos = in.getPosition();
        in.setPosition(reader.getLabelOffset() + 16*labelIndex);
        this.label = in.readString(16);
        in.setPosition(pos);
        
        // Doc said it was a byte offset, does that mean they're all byte offsets?
        in.setPosition(reader.getListIndecesOffset() + in.readUnsignedInt());
        
        int size = in.readUnsignedInt();
        int listPos = in.getPosition();
        for (int i = 0; i < size; i++) {
            
            in.setPosition(listPos);
            in.setPosition(reader.getStructOffset() + 12*in.readUnsignedInt());
            GffStruct struct = new GffStruct(reader, in);
            data.add(struct);
            listPos += 4;
        }
    }
    
    @Override
    public String getLabel() {
        return label;
    }
    
    @Override
    public Iterator<GffStruct> iterator() {
        return data.iterator();
    }
    
    @Override
    public String toString() {
        
        StringBuilder toString = new StringBuilder(label + ":\n");
        for (GffStruct value : data) {
            toString.append(value.toString());
        }
        return toString.toString().trim().replaceAll("\n", "\n\t");
    }
}
