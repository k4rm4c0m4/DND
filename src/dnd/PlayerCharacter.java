package dnd;

import java.util.Map;
import java.util.EnumMap;

public class PlayerCharacter extends Character
{
    private String background;
        public String getBackground()
            { return background; }
        public void setBackground(String background)
            { this.background = background; }

    private int hitDice;
        public int getHitDice()
            { return hitDice; }
        public void setHitDice(int hitDice)
            { this.hitDice = hitDice; }

    private int proficiencyBonus;
        public int getProficiencyBonus()
            { return proficiencyBonus; }
        public void setProficiencyBonus(int proficiencyBonus )
            { this.proficiencyBonus = proficiencyBonus; }

    private Container inventory;
        public Container getInventory()
            { return inventory; }

    private Map<DNDGame.Attribute, Boolean> savingThrowProficiencyMap = new EnumMap<>(DNDGame.Attribute.class);
        public Boolean getSavingThrowProficiency(DNDGame.Attribute attribute)
        {
            return savingThrowProficiencyMap.get(attribute);
        }
        public void setSavingThrowProficiency(DNDGame.Attribute attribute, Boolean val)
        {
            savingThrowProficiencyMap.put(attribute, val);
        }
        public int rollSavingThrow(DNDGame.Attribute attribute)
        {
            int _return = DNDGame.rollDice(20, 0);
            _return += getAttributeModifier(attribute);
            if(getSavingThrowProficiency(attribute))
                _return += getProficiencyBonus();
            return _return;
        }

    private Map<DNDGame.Skill, Boolean> skillProficiencyMap = new EnumMap<>(DNDGame.Skill.class);
        public Boolean getSkillProficiency(DNDGame.Skill skill)
        {
            return skillProficiencyMap.get(skill);
        }
        public void setSkillProficiency(DNDGame.Skill skill, Boolean val)
        {
            skillProficiencyMap.put(skill, val);
        }
        public int rollSkill(DNDGame.Skill skill)
        {
            int _return = DNDGame.rollDice(20, 0);
            _return += getAttributeModifier(DNDGame.getSkillAttribute(skill));
            if(getSkillProficiency(skill))
                _return += getProficiencyBonus();
            return _return;
        }

    PlayerCharacter(String name, int handcount)
    {
        super(name, handcount);
        inventory = new Container(-1);

        for(DNDGame.Skill skill : DNDGame.Skill.values())
        {
            setSkillProficiency(skill, false);
        }

        for(DNDGame.Attribute attribute : DNDGame.Attribute.values())
        {
            setSavingThrowProficiency(attribute, false);
        }
    }
}