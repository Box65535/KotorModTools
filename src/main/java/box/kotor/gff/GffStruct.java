package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class GffStruct implements GffValue, Iterable<GffValue> {
    
    private final Map<String, GffValue> data = new LinkedHashMap<>();
    private final int id;
    private final String label;
    
    protected GffStruct() {
        this.id = -1;
        this.label = null;
    }
    
    public GffStruct(int id, GffValue value) {
        this.id = id;
        this.label = null;
        add(value);
    }
    
    GffStruct(GffReader reader, KotorDataInput in) {
        this(reader, in, null);
    }
    
    private GffStruct(GffReader reader, KotorDataInput in, String label) {
        this.label = label;
        id = in.readUnsignedInt();
        load(reader, in);
    }
    
    protected void load(GffReader reader, KotorDataInput in) {
    
        int index = in.readUnsignedInt();
        int size = in.readUnsignedInt();
    
        int structPos = in.getPosition();
    
        if (size == 1) {
            in.setPosition(reader.getFieldOffset() + index*12);
            add(reader, in);
        }
        else {
        
            int fieldIndecesPos = reader.getFieldIndecesOffset() + index;
            for (int i = 0; i < size; i++) {
            
                in.setPosition(fieldIndecesPos);
                in.setPosition(reader.getFieldOffset() + in.readUnsignedInt()*12);
                add(reader, in);
                fieldIndecesPos += 4;
            }
        }
    
        in.setPosition(structPos);
    }
    
    private void add(GffReader reader, KotorDataInput in) {
        
        int type = in.readUnsignedInt();
        GffValue value;
    
        switch (type) {
            case 0: value = new GffByte(reader, in);
                break;
            case 1: value = new GffChar(reader, in);
                break;
            case 2: value = new GffWord(reader, in);
                break;
            case 3: value = new GffShort(reader, in);
                break;
            case 4: value = new GffDWord(reader, in);
                break;
            case 5: value = new GffInt(reader, in);
                break;
            case 6: value = new GffDWord64(reader, in);
                break;
            case 7: value = new GffInt64(reader, in);
                break;
            case 8: value = new GffFloat(reader, in);
                break;
            case 9: value = new GffDouble(reader, in);
                break;
            case 10: value = new GffCExoString(reader, in);
                break;
            case 11: value = new GffResRef(reader, in);
                break;
            case 12: value = new GffCExoLocString(reader, in);
                break;
            case 13: value = new GffVoid(reader, in);
                break;
            case 15: value = new GffList(reader, in);
                break;
            case 14:
    
                int labelIndex = in.readUnsignedInt();
    
                int pos = in.getPosition();
                in.setPosition(reader.getLabelOffset() + 16*labelIndex);
                String label = in.readString(16);
                in.setPosition(pos);
                
                value = new GffStruct(reader, in, label);
                
                break;
            default: throw new IllegalArgumentException("Can't construct type " + type);
        }
        
        add(value);
    }
    
    public void add(GffValue value) {
        data.put(value.getLabel(), value);
    }
    
    public void remove(String label) {
        data.remove(label);
    }
    
    public GffValue get(String label) {
        return data.get(label);
    }
    
    public boolean contains(String label) {
        return data.containsKey(label);
    }
    
    public byte getByte(String label) {
        return ((GffByte)get(label)).get();
    }
    
    public char getChar(String label) {
        return ((GffChar)get(label)).get();
    }
    
    public short getShort(String label) {
        return ((GffShort)get(label)).get();
    }
    
    public short getWord(String label) {
        return ((GffWord)get(label)).get();
    }
    
    public int getInt(String label) {
        return ((GffInt)get(label)).get();
    }
    
    public int getDWord(String label) {
        return ((GffDWord)get(label)).get();
    }
    
    public long getInt64(String label) {
        return ((GffInt64)get(label)).get();
    }
    
    public long getDWord64(String label) {
        return ((GffDWord64)get(label)).get();
    }
    
    public float getFloat(String label) {
        return ((GffFloat)get(label)).get();
    }
    
    public double getDouble(String label) {
        return ((GffByte)get(label)).get();
    }
    
    public byte[] getVoid(String label) {
        return ((GffVoid)get(label)).get();
    }
    
    public String getCExoString(String label) {
        return ((GffCExoString)get(label)).get();
    }
    
    public String getResRef(String label) {
        return ((GffResRef)get(label)).get();
    }
    
    public GffList getList(String label) {
        return (GffList)get(label);
    }
    
    public GffCExoLocString getCExoLocString(String label) {
        return (GffCExoLocString)get(label);
    }
    
    public GffStruct getStruct(String label) {
        return (GffStruct)get(label);
    }
    
    @Override
    public String getLabel() {
        return label;
    }
    
    @Override
    public Iterator<GffValue> iterator() {
        return data.values().iterator();
    }
    
    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("ID " + id + "\n");
        for (GffValue value : data.values()) {
            toString.append("\t").append(value).append("\n");
        }
        return toString.toString();
    }
}
