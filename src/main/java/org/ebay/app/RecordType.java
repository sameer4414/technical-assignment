package org.ebay.app;

public enum RecordType {
    NAME(0),
    GENDER(1),
    DATE(2);

    private final int index;

    RecordType(final int newIndex){
        index = newIndex;
    }

    public int getIndex(){return index;}
}
