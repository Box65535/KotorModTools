package box.kotor.io;

public class KotorDataInput {
    
    private final byte[] bytes;
    private int pos = 0;
    
    public KotorDataInput(byte[] bytes) {
        this.bytes = bytes;
    }
    
    public byte readByte() {
        byte b = bytes[pos];
        pos++;
        return b;
    }
    
    public byte[] readBytes(int length) {
        length = Math.min(length, bytes.length - pos);
        byte[] retval = new byte[length];
        System.arraycopy(bytes, pos, retval, 0, length);
        pos += length;
        return  retval;
    }
    
    public short readUnsignedShort() {
        int b1 = Byte.toUnsignedInt(bytes[pos+0]);
        int b2 = Byte.toUnsignedInt(bytes[pos+1]);
        pos += 2;
        return (short)((b2 << 8) + (b1 << 0));
    }
    
    public int readUnsignedInt() {
        int b1 = Byte.toUnsignedInt(bytes[pos+0]);
        int b2 = Byte.toUnsignedInt(bytes[pos+1]);
        int b3 = Byte.toUnsignedInt(bytes[pos+2]);
        int b4 = Byte.toUnsignedInt(bytes[pos+3]);
        pos += 4;
        return ((b4 << 24) + (b3 << 16) + (b2 << 8) + (b1 << 0));
    }
    
    public float readFloat() {
        return Float.intBitsToFloat(readUnsignedInt());
    }
    
    public String readString(char terminator) {
        
        int length = 0;
        while (pos + length < bytes.length && bytes[pos + length] != terminator)
            length++;
        
        return readString(length);
    }
    
    public String readString(int length) {
    
        length = Math.min(length, bytes.length - pos);
        
        char[] chars = new char[length];
        for (int i = 0; i < length; i++)
            chars[i] = (char) bytes[pos + i];
        
        pos += length;
        return new String(chars).replaceFirst("\0*$", "");
    }
    
    public int getPosition() {
        return pos;
    }
    
    public void setPosition(int position) {
        this.pos = position;
    }
    
    public boolean canRead() {
        return pos < bytes.length;
    }
}
