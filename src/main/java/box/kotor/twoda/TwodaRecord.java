package box.kotor.twoda;

import box.kotor.io.KotorDataInput;
import box.kotor.io.KotorDataOutput;

import java.io.*;

public interface TwodaRecord {
    
    void read(KotorDataInput in);
    
    void write(KotorDataOutput out);
}
