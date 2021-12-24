/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

 package dnd;

import java.util.Map;
import java.util.EnumMap;

public class PlayerCharacter extends Character
{
    private Map<DND.Skill, Boolean> skillProficiencies = new EnumMap<>(DND.Skill.class);

    /* functions */
    public int getSkillValue(DND.Skill skill)
    {
        int _return = 0, attribute_value = 0, relevant_modifier = 0, bonus = 0;
        DND.Attribute skill_attribute;
        boolean proficiency;

        skill_attribute = DND.getSkillAttribute(skill); 
        attribute_value = getAttribute(skill_attribute);
        relevant_modifier = ( attribute_value - 10 ) / 2;
        proficiency = getProficiency(skill);

        if(proficiency)
        {
            bonus = 2;
        }

        _return = bonus + relevant_modifier;

        return _return;
    }


    public int skill_roll(DND.Skill skill)
    {
        int bonus = getSkillValue(skill);
        int roll = DND.rollDX(20);

        return roll + bonus;
    }

    /* Getter and setter functions */
    public boolean getProficiency(DND.Skill skill) { return skillProficiencies.get(skill); }
    public void setProficiency(DND.Skill skill, boolean condition) { skillProficiencies.put(skill, condition); }

    public PlayerCharacter(String name)
    {
        super(name);

        for(DND.Skill skill : DND.Skill.values())
        {
            setProficiency(skill, false);
        }
    }
}