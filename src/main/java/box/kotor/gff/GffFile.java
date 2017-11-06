package box.kotor.gff;

import box.kotor.gff.io.GffReader;
import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorFile;
public class GffFile extends GffStruct implements KotorFile {
    
    @Override
    public void read(byte[] bytes) {
    
        KotorDataInput in = new KotorDataInput(bytes);
    
        in.readUnsignedInt();
        in.readUnsignedInt();
    
        GffReader reader = new GffReader(in);
        in.readUnsignedInt();
        load(reader, in);
    }
    
    @Override
    public byte[] write() {
        return new byte[0];
    }
}
