package box.kotor.gff.io;

import box.kotor.io.KotorDataOutput;

public class GffWriter {
    
    private final String fileType;
    private final KotorDataOutput structArray = new KotorDataOutput();
    private final KotorDataOutput fieldArray = new KotorDataOutput();
    private final LabelWriter labelArray = new LabelWriter();
    private final KotorDataOutput fieldDataBlock = new KotorDataOutput();
    private final KotorDataOutput fieldIndecesArray = new KotorDataOutput();
    private final KotorDataOutput listIndecesArray = new KotorDataOutput();
    
    public GffWriter(String fileType) {
        this.fileType = fileType;
    }
    
    public KotorDataOutput getStructArray() {
        return structArray;
    }
    
    public KotorDataOutput getFieldArray() {
        return fieldArray;
    }
    
    public LabelWriter getLabelArray() {
        return labelArray;
    }
    
    public KotorDataOutput getFieldDataBlock() {
        return fieldDataBlock;
    }
    
    public KotorDataOutput getFieldIndecesArray() {
        return fieldIndecesArray;
    }
    
    public KotorDataOutput getListIndecesArray() {
        return listIndecesArray;
    }
    
    public byte[] toByteArray() {
        
        KotorDataOutput full = new KotorDataOutput();
        
        full.writeString(fileType.toCharArray());
        full.writeString("V3.2".toCharArray());
        
        byte[] structArrayBytes = structArray.toByteArray();
        byte[] fieldArrayBytes = fieldArray.toByteArray();
        byte[] labelArrayBytes = labelArray.toByteArray();
        byte[] fieldDataBlockBytes = fieldDataBlock.toByteArray();
        byte[] fieldIndecesArrayBytes = fieldIndecesArray.toByteArray();
        byte[] listIndecesArrayBytes = listIndecesArray.toByteArray();
        
        return full.toByteArray();
    }
}
