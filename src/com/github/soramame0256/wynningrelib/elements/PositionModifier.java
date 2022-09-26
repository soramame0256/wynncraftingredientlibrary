package com.github.soramame0256.wynningrelib.elements;

import com.github.soramame0256.wynningrelib.elements.enums.EnumPositionModifier;

public class PositionModifier {
    private final EnumPositionModifier positionModifier;
    private final int value;
    public PositionModifier(EnumPositionModifier positionModifier, int value){
        this.positionModifier = positionModifier;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public EnumPositionModifier getPositionModifier() {
        return positionModifier;
    }
}
