package box.kotor.tlk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;

/**
 * Created on 10/29/2017.
 */
public class TlkString {
    
    private final String text;
    private final String soundRef;
    
    public TlkString(String text) {
        this.text = text;
        this.soundRef = null;
    }
    
    public TlkString(String text, String soundRef) {
        this.text = text;
        this.soundRef = soundRef;
    }
    
    TlkString(BufferedReader reader) throws IOException {
        this.text = null;
        this.soundRef = null;
        for (int i = 0; i < 1000; i++)
            System.out.println(reader.readLine());
    }
    
    public String getText() {
        return text;
    }
    
    public String getSoundRef() {
        return soundRef;
    }
    
    void write(BufferedWriter writer) throws IOException {
    
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        TlkString tlkString = (TlkString) o;
        
        if (text != null ? !text.equals(tlkString.text) : tlkString.text != null) return false;
        return soundRef != null ? soundRef.equals(tlkString.soundRef) : tlkString.soundRef == null;
    }
    
    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (soundRef != null ? soundRef.hashCode() : 0);
        return result;
    }
}
