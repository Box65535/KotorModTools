package box.kotor.twoda;

import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TwodaFile<R extends TwodaRecord> implements KotorFile {
    
    @Override
    public void read(byte[] bytes) {
    
    }
    
    @Override
    public byte[] write() {
        return new byte[0];
    }
}
