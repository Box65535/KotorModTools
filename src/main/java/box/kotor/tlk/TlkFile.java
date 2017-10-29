package box.kotor.tlk;

import java.io.*;
import java.util.*;

public class TlkFile implements Iterable<TlkString> {
    
    private final String path;
    private final List<TlkString> strings = new ArrayList<>();
    private final Map<TlkString, Integer> indeces = new HashMap<>();
    
    public TlkFile(String path) {
        this.path = path;
    }
    
    public void read() throws IOException {
        
        strings.clear();
        indeces.clear();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            TlkString string = new TlkString(reader);
            add(string);
        }
    }
    
    public void write() throws IOException {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (TlkString string : strings)
                string.write(writer);
        }
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
    
    public void remove(int index) {
        TlkString string = strings.remove(index);
        int newIndex = strings.indexOf(string);
        if (newIndex == -1)
            indeces.remove(string);
        else
            indeces.put(string, newIndex);
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
    
    @Override
    public Iterator<TlkString> iterator() {
        return new TlkIterator(this);
    }
}
