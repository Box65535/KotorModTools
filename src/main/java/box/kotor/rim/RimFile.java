package box.kotor.rim;

import box.kotor.io.FileType;
import box.kotor.io.KotorFile;
import box.kotor.io.PackedFile;
import box.kotor.io.KotorDataInput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RimFile implements KotorFile, Iterable<PackedFile> {
    
    private final List<PackedFile> packedFiles = new ArrayList<>();
    
    @Override
    public void read(byte[] bytes) {
        
        packedFiles.clear();
        
        KotorDataInput in = new KotorDataInput(bytes);
    
        if (!in.readString(8).equals("RIM V1.0"))
            throw new IllegalArgumentException("RIM Header corrupted");
        in.readUnsignedInt();
        int size = in.readUnsignedInt();
        int offset = in.readUnsignedInt();
        
        in.setPosition(offset);
        for (int i = 0; i < size; i++) {
            
            String resref = in.readString(16);
            FileType fileType = FileType.typeFor(in.readUnsignedShort());
            in.readUnsignedInt(); // ?
            in.readUnsignedShort(); // ?
            int fileOffset = in.readUnsignedInt();
            int fileSize = in.readUnsignedInt();
            
            int pos = in.getPosition();
            in.setPosition(fileOffset);
            byte[] fileBytes = in.readBytes(fileSize);
            in.setPosition(pos);
            
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
