package com.github.soramame0256.wynningrelib;

import com.github.soramame0256.wynningrelib.elements.*;
import com.github.soramame0256.wynningrelib.elements.enums.EnumIdentification;
import com.github.soramame0256.wynningrelib.elements.enums.EnumItemOnlyId;
import com.github.soramame0256.wynningrelib.elements.enums.EnumPositionModifier;
import com.github.soramame0256.wynningrelib.elements.enums.EnumSkill;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ingredient {
    private final String NAME;
    private final String DISPLAYED_NAME;
    private final List<Identification> IDENTIFICATIONS;
    private final List<ItemOnlyId> ITEM_ONLY_IDS;
    private final List<PositionModifier> POSITION_MODIFIERS;
    private final List<EnumSkill> SKILLS;
    private Ingredient(String name, String displayedName, List<Identification> identifications, List<ItemOnlyId> itemOnlyIds, List<PositionModifier> positionModifiers, List<EnumSkill> skills) {
        this.NAME = name;
        this.DISPLAYED_NAME = displayedName;
        this.IDENTIFICATIONS = identifications;
        this.ITEM_ONLY_IDS = itemOnlyIds;
        this.POSITION_MODIFIERS = positionModifiers;
        this.SKILLS = skills;

    }
    public List<Identification> getIds() {
        return IDENTIFICATIONS;
    }
    public String getName() {
        return NAME;
    }
    public String getDisplayedName() {
        return DISPLAYED_NAME;
    }
    public List<ItemOnlyId> getItemOnlyIds() {
        return ITEM_ONLY_IDS;
    }
    public List<PositionModifier> getPositionModifiers() {
        return POSITION_MODIFIERS;
    }
    public List<EnumSkill> getSkills() {
        return SKILLS;
    }
    public Identification searchIdentification(EnumIdentification eid){
        for(Identification ids : this.getIds()){
            if (ids.getId().name().equals(eid.name())) return ids;
        }
        return null;
    }
    public static Ingredient getIngredientFromJson(JsonObject jo){
        List<Identification> identifications = new ArrayList<>();
        List<ItemOnlyId> itemOnlyIds = new ArrayList<>();
        List<PositionModifier> positionModifiers = new ArrayList<>();
        List<EnumSkill> skills = new ArrayList<>();
        for(Map.Entry<String, JsonElement> entry : jo.get("identifications").getAsJsonObject().entrySet()){
            EnumIdentification eId = EnumIdentification.valueOf(entry.getKey());
            JsonObject idObject = entry.getValue().getAsJsonObject();
            identifications.add(new Identification(eId, idObject.get("minimum").getAsInt(), idObject.get("maximum").getAsInt()));
        }
        if(jo.has("itemOnlyIDs")) {
            for (Map.Entry<String, JsonElement> entry : jo.get("itemOnlyIDs").getAsJsonObject().entrySet()) {
                EnumItemOnlyId eId = EnumItemOnlyId.getFromApiName(entry.getKey());
                itemOnlyIds.add(new ItemOnlyId(eId, entry.getValue().getAsInt()));
            }
        }
        if(jo.has("consumableOnlyIDs")) {
            for (Map.Entry<String, JsonElement> entry : jo.get("consumableOnlyIDs").getAsJsonObject().entrySet()) {
                EnumItemOnlyId eId = EnumItemOnlyId.getFromApiName(entry.getKey());
                itemOnlyIds.add(new ItemOnlyId(eId, entry.getValue().getAsInt()));
            }
        }
        for(Map.Entry<String, JsonElement> entry : jo.get("ingredientPositionModifiers").getAsJsonObject().entrySet()){
            for(EnumPositionModifier enumPositionModifier : EnumPositionModifier.values()){
                if(enumPositionModifier.name().equalsIgnoreCase(entry.getKey())){
                    positionModifiers.add(new PositionModifier(enumPositionModifier, entry.getValue().getAsInt()));
                    break;
                }
            }
        }
        for(JsonElement je : jo.get("skills").getAsJsonArray()){
            skills.add(EnumSkill.valueOf(je.getAsString()));
        }
        return new Ingredient(jo.get("name").getAsString(), jo.has("displayName") ? jo.get("displayName").getAsString() : jo.get("name").getAsString(), identifications, itemOnlyIds, positionModifiers, skills);
    }
}
