package com.github.soramame0256.wynningrelib.elements.enums;

import static com.github.soramame0256.wynningrelib.elements.enums.EnumIdType.CONSUMABLE;
import static com.github.soramame0256.wynningrelib.elements.enums.EnumIdType.ITEM;

public enum EnumItemOnlyId {
    DURABILITYMODIFIER("durabilityModifier",ITEM),
    STRENGTHREQUIREMENT("strengthRequirement",ITEM),
    DEXTERITYREQUIREMENT("dexterityRequirement",ITEM),
    INTELLIGENCEREQUIREMENT("intelligenceRequirement",ITEM),
    DEFENCEREQUIREMENT("defenceRequirement",ITEM),
    AGILITYREQUIREMENT("agilityRequirement",ITEM),
    DURATION("duration",CONSUMABLE),
    CHARGE("charge",CONSUMABLE);
    public String apiName;
    public EnumIdType type;
    EnumItemOnlyId(String apiName, EnumIdType type) {
        this.apiName = apiName;
        this.type = type;
    }
    public static EnumItemOnlyId getFromApiName(String apiName){
        for(EnumItemOnlyId id : EnumItemOnlyId.values()){
            if(id.apiName.equals(apiName)) return id;
        }
        return null;
    }
}
