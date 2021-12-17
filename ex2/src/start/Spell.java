package start;

import java.util.ArrayList;

public class Spell {

    public String name;
    public int level;
    public  String description;
    public String components;
    public int spell_resistance;
    public String class_linked;
    public String creature;
    public String url;
    public String urlImage;

    public Spell(){}


    public Spell(String name, int level, String description, String components, int spell_resistance, String class_linked, String url, String creature, String urlImage) {
        this.name = name;
        this.level = level;
        this.description = description;
        this.components = components;
        this.spell_resistance = spell_resistance;
        this.class_linked = class_linked;
        this.url = url;
        this.creature = creature;
        this.urlImage = urlImage;
    }

    public Spell(String name, int level, String components, String creature) {
        this.name = name;
        this.level = level;
        this.components = components;
        this.creature = creature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public int getSpell_resistance() {
        return spell_resistance;
    }

    public void setSpell_resistance(int spell_resistance) {
        this.spell_resistance = spell_resistance;
    }

    public String getClass_linked() {
        return class_linked;
    }

    public void setClass_linked(String class_linked) {
        this.class_linked = class_linked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreature() {
        return creature;
    }

    public void setCreature(String creature) {
        this.creature = creature;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    ArrayList<Creatures> listCreatures = new ArrayList<Creatures>();

    public ArrayList<Creatures> getListCreatures() {
        return listCreatures;
    }

    public void setListCreatures(ArrayList<Creatures> listCreatures) {
        this.listCreatures = listCreatures;
    }

    public void buildListOfCreatures(){
        this.listCreatures.clear();
        if(this.getCreature().contains(":")) {
            String[] parts = this.getCreature().split(":");
            for (int i = 0; i < parts.length; i++) {
                this.listCreatures.add(new Creatures(parts[i], ""));
            }
        }
    }
}
