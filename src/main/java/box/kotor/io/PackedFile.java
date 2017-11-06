package box.kotor.io;

import box.kotor.gff.GffFile;

public class PackedFile {
    
    private final String resref;
    private final FileType fileType;
    private final byte[] bytes;
    
    public PackedFile(String resref, FileType fileType, byte[] bytes) {
        this.resref = resref;
        this.fileType = fileType;
        this.bytes = bytes;
    }
    
    public FileType getFileType() {
        return fileType;
    }
    
    public ContentType getContentType() {
        return fileType.getContentType();
    }
    
    public String getFileName() {
        return resref + "." + fileType.getExtension();
    }
    
    public byte[] getBytes() {
        return bytes;
    }
    
    public GffFile toGffFile() {
        
        if (getContentType() != ContentType.GFF)
            throw new IllegalArgumentException("Content type " + getContentType() + " cannot be parsed to GFF");
        
        GffFile file = new GffFile();
        file.read(bytes);
        return file;
    }
    
    public void printBytes() {
        
        KotorDataInput in = new KotorDataInput(bytes);
        
        while (in.canRead()) {
        
            byte[] byteLine = in.readBytes(16);
            for (byte b : byteLine)
                System.out.print(String.format("%02X", b) + " ");
            System.out.print("\t\t");
        
            for (byte b : byteLine) {
                char c = (char)b;
                switch(c) {
                    case '\n': System.out.print("\\n");
                        break;
                    case '\t': System.out.print("\\t");
                        break;
                    default: System.out.print(" " + Character.toString(c));
                }
            }
            System.out.print("\n");
        }
    }
}
