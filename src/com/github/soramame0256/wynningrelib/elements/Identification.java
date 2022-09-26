package com.github.soramame0256.wynningrelib.elements;

import com.github.soramame0256.wynningrelib.elements.enums.EnumIdentification;

public class Identification {
    private final EnumIdentification id;
    private final int min;
    private final int max;
    public Identification(EnumIdentification id, int min, int max){
        this.id = id;
        this.min = min;
        this.max = max;
    }
    public int getMax() {
        return max;
    }
    public int getMin() {
        return min;
    }
    public EnumIdentification getId() {
        return id;
    }
}
