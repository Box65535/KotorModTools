package box.kotor.test;

import box.kotor.gff.GffFile;
import box.kotor.io.FileType;
import box.kotor.io.PackedFile;
import box.kotor.rim.RimFile;
import box.kotor.tlk.TlkFile;

import java.io.IOException;

public class RimTest {
    
    public static void main(String[] args) throws IOException {
        
        TlkFile tlkFile = new TlkFile();
        tlkFile.read("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Knights of the Old Republic II\\dialog.tlk");
        RimFile rimFile = new RimFile();
        rimFile.read("C:\\Program Files (x86)\\Steam\\steamapps\\common\\Knights of the Old Republic II\\Modules\\352NAR_s.rim");
        
        surveyEnemies(rimFile, tlkFile);
    }
    
    private static void surveyEnemies(RimFile rimFile, TlkFile tlkFile) {
    
        for (PackedFile packedFile : rimFile) {
            if (packedFile.getFileType() == FileType.UTC) {
            
                GffFile gffFile = packedFile.toGffFile();
                System.out.println(packedFile.getFileName());
                System.out.println(tlkFile.get(gffFile.getCExoLocString("FirstName").getStrRef()).getText());
                System.out.println(gffFile.get("Tag"));
                System.out.println(gffFile.get("Conversation"));
                System.out.println(gffFile.get("FactionID"));
                System.out.println(gffFile.get("ScriptSpawn"));
                System.out.println(gffFile.get("ChallengeRating"));
                System.out.println(gffFile.getList("Equip_ItemList"));
                System.out.println();
            }
        }
    }
    
    private static void surveyTreasures(RimFile rimFile, TlkFile tlkFile) {
        
        for (PackedFile packedFile : rimFile) {
            if (packedFile.getFileType() == FileType.UTP) {
                
                GffFile gffFile = packedFile.toGffFile();
                System.out.println(packedFile.getFileName());
                System.out.println(tlkFile.get(gffFile.getCExoLocString("LocName").getStrRef()).getText());
                System.out.println(gffFile.get("Tag"));
                System.out.println(gffFile.get("Conversation"));
                System.out.println(gffFile.get("OnHeartbeat"));
                System.out.println(gffFile.get("OnUnlock"));
                System.out.println(gffFile.get("Locked"));
                System.out.println(gffFile.getList("ItemList"));
                System.out.println();
            }
        }
    }
    
    
}
