package dnd;

import java.util.EnumMap;

public class PlayerCharacter extends Character
{
    private String background;
    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }

    private int hitDice;
    public int getHitDice() { return hitDice; }
    public void setHitDice(int hitDice) { this.hitDice = hitDice; }

    private Container inventory;
    public Container getInventory() { return inventory; }

    private EnumMap<DNDGame.Attribute, Boolean> savingThrowProficiencyMap = new EnumMap<>(DNDGame.Attribute.class);
    public Boolean getSavingThrowProficiency(DNDGame.Attribute at)
    {
        return savingThrowProficiencyMap.get(at);
    }

    PlayerCharacter(String name, int handcount)
    {
        super(name, handcount);
        inventory = new Container(-1);
    }
}