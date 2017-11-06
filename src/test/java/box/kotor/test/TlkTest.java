package box.kotor.test;

import box.kotor.tlk.TlkFile;
import box.kotor.tlk.TlkString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TlkTest {
    
    public static void main(String[] args) throws IOException {
        
        TlkFile tlkFile = new TlkFile();
        tlkFile.read("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Knights of the Old Republic II\\dialog.tlk");
        tlkFile.write("temp.tlk");
        
        TlkFile copy = new TlkFile();
        copy.read("temp.tlk");
        Files.delete(Paths.get("temp.tlk"));
        
        System.out.println(tlkFile.equals(copy));
    }
}
