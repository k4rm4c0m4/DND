package dnd;

import java.util.Map;
import java.util.EnumMap;

public class NonPlayerCharacter extends Character
{
    private Map<DNDGame.Attribute, Integer> savingThrowBonusMap = new EnumMap<>(DNDGame.Attribute.class);
        public void setSavingThrowBonus(DNDGame.Attribute attribute, Integer val)
            { savingThrowBonusMap.put(attribute, val); }
        public Integer getSavingThrowBonus(DNDGame.Attribute attribute)
            { return savingThrowBonusMap.get(attribute); }
        public int rollSavingThrow(DNDGame.Attribute attribute)
        {
            int roll = DNDGame.rollDice(20, 1);
            return roll + savingThrowBonusMap.get(attribute);
        }

    NonPlayerCharacter(String name, int handcount)
    {
        super(name, handcount);

        for(DNDGame.Attribute attribute : DNDGame.Attribute.values())
        {
            setSavingThrowBonus(attribute, 0);
        }
    }
}