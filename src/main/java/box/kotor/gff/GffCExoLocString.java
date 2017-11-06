package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

import java.util.ArrayList;
import java.util.List;

public class GffCExoLocString extends GffField {
    
    private final int strRef;
    private final List<GffCExoString> substrings = new ArrayList<>();
    
    GffCExoLocString(GffReader reader, KotorDataInput in) {
        super(reader, in);
        
        int offset = reader.getFieldDataOffset() + in.readUnsignedInt();
        int pos = in.getPosition();
    
        in.setPosition(offset);
        in.readUnsignedInt();
        strRef = in.readUnsignedInt();
        int count = in.readUnsignedInt();
        for (int i = 0; i < count; i++) {
            in.readUnsignedInt();
            substrings.add(new GffCExoString(in));
        }
    
        in.setPosition(pos);
    }
    
    public int getStrRef() {
        return strRef;
    }
    
    @Override
    public String toString() {
        if (substrings.size() == 0)
            return getLabel() + ": " + strRef;
        else if (substrings.size() == 1)
            return getLabel() + ": " + strRef + " + " + substrings.get(0).toString();
        else
            return getLabel() + ": " + strRef + " + " + substrings.toString();
    }
}
