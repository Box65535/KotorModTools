package box.kotor.test;

import box.kotor.io.KotorDataInput;
import box.kotor.twoda.TwodaFile;
import box.kotor.twoda.TwodaRecord;

import java.io.DataOutput;
import java.io.IOException;

public class TwodaTest {
    
    public static void main(String[] args) throws IOException {
        
        TwodaFile twodaFile = new TwodaFile();
        twodaFile.read("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Knights of the Old Republic II\\override\\itemcreate.2da");
        
        System.out.println(twodaFile);
    }
}
