package box.kotor.io;

import java.io.ByteArrayOutputStream;

public class KotorDataOutput {
    
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    
    public void writeByte(int value) {
        baos.write(value);
    }
    
    public void writeString(char[] string) {
        for (char c : string)
            baos.write(c);
    }
    
    public void writeString(char[] string, int minLength) {
        int i = 0;
        for (; i < string.length; i++)
            baos.write(string[i]);
        for (; i < minLength; i++)
            baos.write(0);
    }
    
    public void writeString(char[] string, char terminator) {
        writeString(string);
        baos.write(terminator);
    }
    
    public void writeInt(int value) {
        baos.write((value >>> 0) & 0xFF);
        baos.write((value >>> 8) & 0xFF);
        baos.write((value >>> 16) & 0xFF);
        baos.write((value >>> 24) & 0xFF);
    }
    
    public void writeFloat(float value) {
        writeInt(Float.floatToIntBits(value));
    }
    
    public byte[] toByteArray() {
        return baos.toByteArray();
    }
}
