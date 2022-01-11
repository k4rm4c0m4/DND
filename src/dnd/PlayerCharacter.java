/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import java.util.Map;
import java.util.EnumMap;

public class PlayerCharacter extends Character
{
    private Map<DNDGame.Skill, Boolean> skillProficiencies = new EnumMap<>(DNDGame.Skill.class);

    /* functions */
    public int getSkillValue(DNDGame.Skill skill)
    {
        int _return = 0, attribute_value = 0, relevant_modifier = 0, bonus = 0;
        DNDGame.Attribute skill_attribute;
        boolean proficiency;

        skill_attribute = DNDGame.getSkillAttribute(skill); 
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


    public int skill_roll(DNDGame.Skill skill)
    {
        int bonus = getSkillValue(skill);
        int roll = DNDGame.rollDX(20);

        return roll + bonus;
    }

    /* Getter and setter functions */
    public boolean getProficiency(DNDGame.Skill skill) { return skillProficiencies.get(skill); }
    public void setProficiency(DNDGame.Skill skill, boolean condition) { skillProficiencies.put(skill, condition); }

    public PlayerCharacter(String name)
    {
        super(name);

        for(DNDGame.Skill skill : DNDGame.Skill.values())
        {
            setProficiency(skill, false);
        }
    }
}