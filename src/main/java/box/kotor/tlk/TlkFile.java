package box.kotor.tlk;

import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorDataOutput;
import box.kotor.io.KotorFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class TlkFile implements KotorFile, Iterable<TlkString> {
    
    private final List<TlkString> strings = new ArrayList<>();
    private final Map<TlkString, Integer> indeces = new HashMap<>();
    
    @Override
    public void read(byte[] bytes) {
    
        strings.clear();
        indeces.clear();
    
        KotorDataInput in = new KotorDataInput(bytes);
    
        if (!in.readString(8).equals("TLK V3.0"))
            throw new IllegalArgumentException("TLK Header corrupted");
        if (in.readUnsignedInt() != 0)
            throw new IllegalArgumentException("Non-English TLK file");
        int numberOfStrings = in.readUnsignedInt();
        int entryOffset = in.readUnsignedInt();
    
        for (int i = 0; i < numberOfStrings; i++) {
            TlkString string = new TlkString(in, entryOffset);
            this.add(string);
        }
    }
    
    @Override
    public byte[] write() {
    
        KotorDataOutput out = new KotorDataOutput();
    
        out.writeString("TLK V3.0".toCharArray());
        out.writeInt(0); // Language
        out.writeInt(length()); // Number of Strings
        out.writeInt(20 + 40*length()); // Entry Offset
    
        int offset = 0;
        for (TlkString string : this)
            offset = string.writeHeader(out, offset);
    
        for (TlkString string : this)
            string.writeText(out);
        
        return out.toByteArray();
    }
    
    public TlkString get(int index) {
        return strings.get(index);
    }
    
    public int add(TlkString string) {
        int index = strings.size();
        strings.add(string);
        if (!indeces.containsKey(string))
            indeces.put(string, index);
        return index;
    }
    
    public int addIfUnique(TlkString string) {
        if (indeces.containsKey(string))
            return indeces.get(string);
        int index = strings.size();
        strings.add(string);
        return index;
    }
    
    public TlkString remove(int index) {
        TlkString string = strings.remove(index);
        int newIndex = strings.indexOf(string);
        if (newIndex == -1)
            indeces.remove(string);
        else
            indeces.put(string, newIndex);
        return string;
    }
    
    public int find(TlkString string) {
        return indeces.getOrDefault(string, -1);
    }
    
    public void append(TlkFile other) {
        for (TlkString string : other.strings)
            add(string);
    }
    
    public int length() {
        return strings.size();
    }
    
    public TlkFile copy() {
        TlkFile copy = new TlkFile();
        for (TlkString string : this)
            copy.add(string);
        return copy;
    }
    
    @Override
    public Iterator<TlkString> iterator() {
        return new TlkIterator(this);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        TlkFile that = (TlkFile) o;
        
        if (!strings.equals(that.strings)) return false;
        return indeces.equals(that.indeces);
    }
    
    @Override
    public int hashCode() {
        int result = strings.hashCode();
        result = 31 * result + indeces.hashCode();
        return result;
    }
}
