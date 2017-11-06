package box.kotor.erf;

import box.kotor.io.FileType;
import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorFile;
import box.kotor.io.PackedFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ErfFile implements KotorFile, Iterable<PackedFile> {
    
    private final Map<Integer, String> localizedStrings = new HashMap<>();
    private final List<PackedFile> packedFiles = new ArrayList<>();
    
    @Override
    public void read(byte[] bytes) {
    
        KotorDataInput in = new KotorDataInput(bytes);
        
        String erfType = in.readString(4);
        if (!in.readString(4).equals("V1.0"))
            throw new IllegalArgumentException("ERF Header corrupted");
        
        int stringCount = in.readUnsignedInt();
        in.readUnsignedInt();
        int fileCount = in.readUnsignedInt();
        
        int stringOffset = in.readUnsignedInt();
        int keyOffset = in.readUnsignedInt();
        int resourceOffset = in.readUnsignedInt();
        
        in.setPosition(stringOffset);
        for (int i = 0; i < stringCount; i++) {
            
            int languageId = in.readUnsignedInt();
            int stringSize = in.readUnsignedInt();
            String string = in.readString(stringSize);
            
            localizedStrings.put(languageId, string);
        }
        
        List<String> resrefs = new ArrayList<>();
        List<FileType> fileTypes = new ArrayList<>();
        in.setPosition(keyOffset);
        for (int i = 0; i < fileCount; i++) {
            
            resrefs.add(in.readString(16));
            int resId = in.readUnsignedInt();
            fileTypes.add(FileType.typeFor(in.readUnsignedShort()));
            
            if (resId != i)
                throw new IllegalArgumentException("Corrupted ResID");
        }
        
        in.setPosition(resourceOffset);
        for (int i = 0; i < fileCount; i++) {
            
            int fileOffset = in.readUnsignedInt();
            int fileSize = in.readUnsignedInt();
            
            int pos = in.getPosition();
            in.setPosition(fileOffset);
            byte[] fileBytes = in.readBytes(fileSize);
            in.setPosition(pos);
            
            String resref = resrefs.get(i);
            FileType fileType = fileTypes.get(i);
            PackedFile packedFile = new PackedFile(resref, fileType, fileBytes);
            packedFiles.add(packedFile);
        }
    }
    
    @Override
    public byte[] write() {
        return new byte[0];
    }
    
    @Override
    public Iterator<PackedFile> iterator() {
        return packedFiles.iterator();
    }
}
