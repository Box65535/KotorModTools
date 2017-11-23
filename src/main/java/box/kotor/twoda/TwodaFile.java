package box.kotor.twoda;

import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TwodaFile implements KotorFile, Iterable<TwodaRecord> {
    
    private static final String ROW_LABEL = "RowLabel";
    private final Map<String, Integer> rowLabels = new HashMap<>();
    private final List<TwodaRecord> records = new ArrayList<>();
    
    @Override
    public void read(byte[] bytes) {
        
        rowLabels.clear();
        records.clear();
        
        KotorDataInput in = new KotorDataInput(bytes);
        
        if (!in.readString(8).equals("2DA V2.b"))
            throw new IllegalArgumentException("2DA Header corrupted");
        
        in.readString('\n'); // Garbage??
        
        List<String> header = Arrays.asList(
                in.readString('\0').split("\t")
        );
        int rowCount = in.readUnsignedInt();
        in.readString('\0'); // Ignore row indeces
        
        
    }
    
    public boolean isEmpty() {
        return records.isEmpty();
    }
    
    public List<String> getHeader() {
        return records.get(0).getHeader();
    }
    
    public TwodaRecord get(int index) {
        return records.get(index);
    }
    
    public int add(TwodaRecord record) {
        
        if (!isEmpty() && !record.getHeader().equals(getHeader()))
            throw new IllegalArgumentException("Non-matching header");
        
        records.add(record);
        int index = records.size();
        
        if (getHeader().contains(ROW_LABEL)) {
            String rowLabel = record.getString(ROW_LABEL);
            
            if (!rowLabels.containsKey(rowLabel))
                rowLabels.put(rowLabel, index);
        }
        
        return index;
    }
    
    public TwodaRecord remove(int index) {
        
        TwodaRecord record = records.remove(index);
        if (record.getHeader().contains(ROW_LABEL)) {
            
            String rowLabel = record.getString(ROW_LABEL);
            
            int replacmentIndex = -1;
            for (int i = 0; i < records.size(); i++) {
                if (records.get(i).getString(ROW_LABEL).equals(rowLabel)) {
                    replacmentIndex = i;
                    break;
                }
            }
            
            if (replacmentIndex == -1)
                rowLabels.remove(rowLabel);
            else
                rowLabels.put(rowLabel, replacmentIndex);
        }
        
        return record;
    }
    
    public int length() {
        return records.size();
    }
    
    public boolean contains(String rowLabel) {
        return rowLabels.containsKey(rowLabel);
    }
    
    public TwodaRecord get(String rowLabel) {
        return records.get(rowLabels.get(rowLabel));
    }
    
    public void clear() {
        rowLabels.clear();
        records.clear();
    }
    
    @Override
    public byte[] write() {
        return new byte[0];
    }
    
    @Override
    public Iterator<TwodaRecord> iterator() {
        return new TwodaIterator(this);
    }
    
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(getHeader().toString()).append("\n");
        for (TwodaRecord record : records)
            string.append(record.toString()).append("\n");
        return string.toString();
    }
}
