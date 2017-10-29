package box.kotor.tlk;

import java.util.Iterator;

class TlkIterator implements Iterator<TlkString> {
    
    private final TlkFile tlkFile;
    private int index = 0;
    
    public TlkIterator(TlkFile tlkFile) {
        this.tlkFile = tlkFile;
    }
    
    @Override
    public boolean hasNext() {
        return index < tlkFile.length();
    }
    
    @Override
    public TlkString next() {
        TlkString string = tlkFile.get(index);
        index++;
        return string;
    }
    
    @Override
    public void remove() {
        tlkFile.remove(index);
        index--;
    }
}
