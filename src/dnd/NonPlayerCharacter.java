package dnd;

import java.util.EnumMap;

public class NonPlayerCharacter extends Character
{
    private EnumMap<DNDGame.Attribute, Integer> saving_throw_bonus_map = new EnumMap<>(DNDGame.Attribute.class);
    public void setSavingThrowBonus(DNDGame.Attribute at, Integer val) { saving_throw_bonus_map.put(at, val); }
    public Integer getSavingThrowBonus(DNDGame.Attribute at) { return saving_throw_bonus_map.get(at); }

    public int rollSavingThrow(DNDGame.Attribute at)
    {
        int roll = DNDGame.roll_dice(20, 1);
        return roll + saving_throw_bonus_map.get(at);
    }

    NonPlayerCharacter(String name, int handcount)
    {
        super(name, handcount);
    }
}