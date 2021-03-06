package box.kotor.tlk;

<<<<<<< HEAD
import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorDataOutput;

import java.io.BufferedWriter;
import java.io.IOException;
=======
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
>>>>>>> e0a09231aa11e5536345d4dc431caf7db42af8ab

/**
 * Created on 10/29/2017.
 */
public class TlkString {
    
    private final String text;
    private final String soundRef;
<<<<<<< HEAD
    private final float soundLength;
    
    public TlkString(String text) {
        this(text, "", 0.0f);
    }
    
    public TlkString(String text, String soundRef, float soundLength) {
        this.text = text;
        this.soundRef = soundRef;
        this.soundLength = 0.0f;
    }
    
    TlkString(KotorDataInput in, int entryOffset) {
        
        int flags = in.readUnsignedInt();
        
        boolean textPresent = (flags & 0x0001) > 0;
        boolean soundPresent = (flags & 0x0002) > 0;
        boolean soundLengthPresent = (flags & 0x0004) > 0;
        
        this.soundRef = in.readString(16);
        int volumeVariance = in.readUnsignedInt(); // Unused
        int pitchVariance = in.readUnsignedInt(); // Unused
        int stringOffset = in.readUnsignedInt();
        int stringSize = in.readUnsignedInt();
        this.soundLength = in.readFloat();
        
        int pos = in.getPosition();
        in.setPosition(entryOffset + stringOffset);
        this.text = in.readString(stringSize);
        in.setPosition(pos);
=======
    
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
>>>>>>> e0a09231aa11e5536345d4dc431caf7db42af8ab
    }
    
    public String getText() {
        return text;
    }
    
    public String getSoundRef() {
        return soundRef;
    }
    
<<<<<<< HEAD
    public float getSoundLength() {
        return soundLength;
    }
    
    int writeHeader(KotorDataOutput out, int offset) {
        
        int flags = 0;
        if (!text.equals(""))
            flags += 1;
        if (!soundRef.equals(""))
            flags += 2;
        if (soundLength != 0.0f)
            flags += 4;
        
        out.writeInt(flags);
        out.writeString(soundRef.toCharArray(), 16);
        out.writeInt(0); // Volume variance
        out.writeInt(0); // Pitch variance
        out.writeInt(offset); // String offset
        out.writeInt(text.length()); // String length
        out.writeFloat(soundLength);
        
        return offset + text.length();
    }
    
    void writeText(KotorDataOutput out) {
        out.writeString(text.toCharArray());
=======
    void write(BufferedWriter writer) throws IOException {
    
>>>>>>> e0a09231aa11e5536345d4dc431caf7db42af8ab
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
