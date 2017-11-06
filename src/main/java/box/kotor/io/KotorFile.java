package box.kotor.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public interface KotorFile {
    
    default void read(String path) throws IOException {
        read(Paths.get(path));
    }
    
    default void read(Path path) throws IOException {
        read(Files.readAllBytes(path));
    }
    
    void read(byte[] bytes);
    
    default void write(String path) throws IOException {
        write(Paths.get(path));
    }
    
    default void write(Path path) throws IOException {
        Files.write(path, write(), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
    
    byte[] write();
    
    default PackedFile toPackedFile(String resref, FileType fileType) {
        return new PackedFile(resref, fileType, write());
    }
}
