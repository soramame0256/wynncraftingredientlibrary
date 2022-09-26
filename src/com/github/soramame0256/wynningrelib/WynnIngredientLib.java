package com.github.soramame0256.wynningrelib;

import com.github.soramame0256.wynningrelib.elements.*;
import com.github.soramame0256.wynningrelib.elements.enums.EnumIdentification;
import com.github.soramame0256.wynningrelib.elements.enums.EnumItemOnlyId;
import com.github.soramame0256.wynningrelib.elements.enums.EnumPositionModifier;
import com.github.soramame0256.wynningrelib.elements.enums.EnumSkill;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WynnIngredientLib {
    private static final String WYNN_CRAFT_API = "https://api.wynncraft.com/v2/ingredient/search/tier/";
    private static WynnIngredientLib INSTANCE;

    static {
        try {
            INSTANCE = new WynnIngredientLib();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final List<Ingredient> ingredients = new ArrayList<>();
    private WynnIngredientLib() throws IOException {
        for (int i = 0; i <= 3; i++) {
            JsonObject jo = DataFetcher.getJsonFromSpecificURL(WYNN_CRAFT_API + i);
            for(JsonElement je : jo.get("data").getAsJsonArray()){
                ingredients.add(Ingredient.getIngredientFromJson(je.getAsJsonObject()));
            }
        }
    }
    private WynnIngredientLib(List<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    /**
     * @return library
     */
    public static WynnIngredientLib getLib(){
        return new WynnIngredientLib(INSTANCE.ingredients);
    }

    /**
     * @param id that for identification search
     * @return instance
     */
    public WynnIngredientLib filterIngredients(EnumIdentification id){
        List<Ingredient> toRemove = new ArrayList<>();
        for(Ingredient ing : ingredients){
            boolean found = false;
            for(Identification ide : ing.getIds()){
                if(ide.getId().equals(id)){
                    found = true;
                    break;
                }
            }
            if(!found) toRemove.add(ing);
        }
        this.ingredients.removeAll(toRemove);
        return this;
    }
    /**
     * @param id that for item only id search
     * @return instance
     */
    public WynnIngredientLib filterIngredients(EnumItemOnlyId id){
        List<Ingredient> toRemove = new ArrayList<>();
        for(Ingredient ing : ingredients){
            boolean found = false;
            for(ItemOnlyId ioi : ing.getItemOnlyIds()){
                if(ioi.getId().equals(id) && ioi.getValue() != 0){
                    found = true;
                    break;
                }
            }
            if(!found) toRemove.add(ing);
        }
        this.ingredients.removeAll(toRemove);
        return this;
    }

    /**
     *
     * @param skill that for skill search
     * @return instance
     */
    public WynnIngredientLib filterIngredients(EnumSkill skill){
        List<Ingredient> toRemove = new ArrayList<>();
        for(Ingredient ing : ingredients){
            if(!ing.getSkills().contains(skill)) toRemove.add(ing);
        }
        this.ingredients.removeAll(toRemove);
        return this;
    }
    /**
     *
     * @param positionModifier that for positionModifier search
     * @return instance
     */
    public WynnIngredientLib filterIngredients(EnumPositionModifier positionModifier){
        List<Ingredient> toRemove = new ArrayList<>();
        for(Ingredient ing : ingredients){
            boolean found = false;
            for(PositionModifier pm : ing.getPositionModifiers()){
                if(pm.getPositionModifier().equals(positionModifier) && pm.getValue() != 0){
                    found = true;
                    break;
                }
            }
            if(!found) toRemove.add(ing);
        }
        this.ingredients.removeAll(toRemove);
        return this;
    }
}
