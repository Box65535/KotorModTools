package box.kotor.twoda;

import java.util.Iterator;

class TwodaIterator implements Iterator<TwodaRecord> {
    
    private final TwodaFile twodaFile;
    private int index = 0;
    
    public TwodaIterator(TwodaFile tlkFile) {
        this.twodaFile = tlkFile;
    }
    
    @Override
    public boolean hasNext() {
        return index < twodaFile.length();
    }
    
    @Override
    public TwodaRecord next() {
        TwodaRecord string = twodaFile.get(index);
        index++;
        return string;
    }
    
    @Override
    public void remove() {
        twodaFile.remove(index);
        index--;
    }
}
