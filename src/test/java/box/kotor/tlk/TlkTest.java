package box.kotor.tlk;

import java.io.IOException;

public class TlkTest {
    
    public static void main(String[] args) throws IOException {
        
        TlkFile tlkFile = new TlkFile("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Knights of the Old Republic II\\dialog.tlk");
        tlkFile.read();
    }
}
