package box.kotor.gff.io;

import box.kotor.io.KotorDataOutput;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on 11/1/2017.
 */
public class LabelWriter {
    
    private final List<String> labels = new ArrayList<>();
    private final KotorDataOutput out = new KotorDataOutput();
    
    public int writeLabel(String label) {
        int index = labels.indexOf(label);
        if (index == -1) {
            index = labels.size();
            labels.add(label);
        }
        out.writeString(label.toCharArray(), 16);
        return index;
    }
    
    public byte[] toByteArray() {
        return out.toByteArray();
    }
}
