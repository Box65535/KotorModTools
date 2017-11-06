package box.kotor.io;

import static box.kotor.io.ContentType.*;

/**
 * Created on 11/5/2017.
 */
public enum FileType {
    
    BMP(1, BINARY),
    TGA(3, BINARY),
    WAV(4, BINARY),
    PLT(6, BINARY),
    INI(7, TEXT),
    TXT(10, TEXT),
    MDL(2002, MODEL),
    NSS(2009, TEXT),
    NCS(2010, BINARY),
    ARE(2012, GFF),
    SET(2013, TEXT),
    IFO(2014, GFF),
    BIC(2015, GFF),
    WOK(2016, MODEL),
    TWODA(2017, TEXT),
    TXI(2022, TEXT),
    GIT(2023, GFF),
    UTI(2025, GFF),
    UTC(2027, GFF),
    DLG(2029, GFF),
    ITP(2030, GFF),
    UTT(2032, GFF),
    DDS(2033, BINARY),
    UTS(2035, GFF),
    LTR(2036, BINARY),
    GENERIC(2017, GFF),
    FAC(2038, GFF),
    UTE(2040, GFF),
    UTD(2042, GFF),
    UTP(2044, GFF),
    DFT(2045, TEXT),
    GIC(2046, GFF),
    GUI(2047, GFF),
    UTM(2051, GFF),
    DWK(2052, MODEL),
    PWK(2053, MODEL),
    JRL(2056, GFF),
    UTW(2058, GFF),
    SSF(2060, BINARY),
    NDB(2064, BINARY),
    PTM(2065, GFF),
    PTT(2066, GFF),
    UNKNOWN(3003, BINARY),
    ;
    
    private final short resType;
    private final String extension;
    private final ContentType contentType;
    
    FileType(int resType, ContentType contentType) {
        this.resType = (short)resType;
        this.contentType = contentType;
        
        if (toString() == "TWODA")
            this.extension = "2da";
        else if (toString() == "GENERIC")
            this.extension = "gff";
        else
            this.extension = toString().toLowerCase();
    }
    
    public short getResType() {
        return resType;
    }
    
    public String getExtension() {
        return extension;
    }
    
    public ContentType getContentType() {
        return contentType;
    }
    
    public static FileType typeFor(String extension) {
        
        extension = extension.toLowerCase().substring(0, 3);
        for (FileType type : FileType.values())
            if (type.extension.equals(extension))
                return type;
        
        throw new IllegalArgumentException("No type with extension " + extension);
    }
    
    public static FileType typeFor(short resType) {
        
        for (FileType type : FileType.values())
            if (type.resType == resType)
                return type;
        
        throw new IllegalArgumentException("No type with resType " + resType);
    }
}
