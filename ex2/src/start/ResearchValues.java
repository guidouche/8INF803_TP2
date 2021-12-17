package start;

import java.util.ArrayList;

public class ResearchValues {

    public ResearchValues(){

    }


    public int minLevel;
    public int maxLevel;

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    //Classes
    Boolean classesmodeOr = true;
    Boolean classesmodeAnd = false;
    ArrayList<String> classesValues = new ArrayList<String>();

    public Boolean getClassesmodeOr() {
        return classesmodeOr;
    }

    public void setClassesmodeOr(Boolean classesmodeOr) {
        this.classesmodeOr = classesmodeOr;
    }

    public Boolean getClassesmodeAnd() {
        return classesmodeAnd;
    }

    public void setClassesmodeAnd(Boolean classesmodeAnd) {
        this.classesmodeAnd = classesmodeAnd;
    }




    //VSM
    Boolean vsmmodeOr = true;
    Boolean vsmmodeAnd = false;
    ArrayList<String> vsmValues = new ArrayList<String>();


    public Boolean getVsmmodeOr() {
        return vsmmodeOr;
    }

    public void setVsmmodeOr(Boolean vsmmodeOr) {
        this.vsmmodeOr = vsmmodeOr;
    }

    public Boolean getVsmmodeAnd() {
        return vsmmodeAnd;
    }

    public void setVsmmodeAnd(Boolean vsmmodeAnd) {
        this.vsmmodeAnd = vsmmodeAnd;
    }




    //Spell text
    Boolean spellTextKeywords = false;
    Boolean spellTextExactPhrase= true;
    String spellText;

    public Boolean getSpellTextKeywords() {
        return spellTextKeywords;
    }

    public void setSpellTextKeywords(Boolean spellTextKeywords) {
        this.spellTextKeywords = spellTextKeywords;
    }

    public Boolean getSpellTextExactPhrase() {
        return spellTextExactPhrase;
    }

    public void setSpellTextExactPhrase(Boolean spellTextExactPhrase) {
        this.spellTextExactPhrase = spellTextExactPhrase;
    }

    public String getSpellText() {
        return spellText;
    }

    public void setSpellText(String spellText) {
        this.spellText = spellText;
    }

    //Spell name
    Boolean spellNameBeginWith = true;
    Boolean spellNameEndWith = false;
    Boolean spellNameContains = false;
    String spellName;

    public Boolean getSpellNameBeginWith() {
        return spellNameBeginWith;
    }

    public void setSpellNameBeginWith(Boolean spellNameBeginWith) {
        this.spellNameBeginWith = spellNameBeginWith;
    }

    public Boolean getSpellNameEndWith() {
        return spellNameEndWith;
    }

    public void setSpellNameEndWith(Boolean spellNameEndWith) {
        this.spellNameEndWith = spellNameEndWith;
    }

    public Boolean getSpellNameContains() {
        return spellNameContains;
    }

    public void setSpellNameContains(Boolean spellNameContains) {
        this.spellNameContains = spellNameContains;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }


    public ArrayList<String> getClassesValues() {
        return classesValues;
    }

    public void setClassesValues(ArrayList<String> classesValues) {
        this.classesValues = classesValues;
    }

    public ArrayList<String> getVsmValues() {
        return vsmValues;
    }

    public void setVsmValues(ArrayList<String> vsmValues) {
        this.vsmValues = vsmValues;
    }
}
