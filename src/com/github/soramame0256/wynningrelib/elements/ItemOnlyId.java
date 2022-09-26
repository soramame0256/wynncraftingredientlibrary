package com.github.soramame0256.wynningrelib.elements;

import com.github.soramame0256.wynningrelib.elements.enums.EnumItemOnlyId;

public class ItemOnlyId {
    private final EnumItemOnlyId id;
    private final int value;
    public ItemOnlyId(EnumItemOnlyId id, int value){
        this.id = id;
        this.value = value;
    }

    public EnumItemOnlyId getId() {
        return id;
    }

    public int getValue() {
        return value;
    }
}
