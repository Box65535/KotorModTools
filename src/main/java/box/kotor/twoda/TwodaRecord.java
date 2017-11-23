package box.kotor.twoda;

import java.util.Arrays;
import java.util.List;

public class TwodaRecord {
    
    private final List<String> header;
    private final Object[] values;
    
    public TwodaRecord(List<String> header) {
        this.header = header;
        this.values = new Object[header.size()];
    }
    
    public List<String> getHeader() {
        return header;
    }
    
    public Object[] getValues() {
        return values;
    }
    
    public void set(String label, Object value) {
        int index = header.indexOf(label);
        values[index] = value;
    }
    
    public Object get(int index) {
        return values[index];
    }
    
    public Object get(String label) {
        return get(header.indexOf(label));
    }
    
    public String getString(String label) {
        return (String)get(label);
    }
    
    public int getInt(String label) {
        return (Integer)get(label);
    }
    
    public float getFloat(String label) {
        return (Float)get(label);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        TwodaRecord record = (TwodaRecord) o;
        
        if (!header.equals(record.header)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(values, record.values);
    }
    
    @Override
    public int hashCode() {
        int result = header.hashCode();
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }
    
    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
