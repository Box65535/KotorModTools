package box.kotor.test;

import box.kotor.io.FileType;
import box.kotor.io.KotorDataInput;
import box.kotor.io.PackedFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HexReader {
    
    public static void main(String[] args) throws IOException {
        read("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Knights of the Old Republic II\\override\\itemcreate.2da");
//        read("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Knights of the Old Republic II\\Modules\\001EBO.rim");
    }
    
    public static void read(String path) throws IOException {
        
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        PackedFile file = new PackedFile("", FileType.GENERIC, bytes);
        file.printBytes();
    }
}
