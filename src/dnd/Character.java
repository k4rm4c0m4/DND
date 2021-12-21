package dnd;

import java.util.Map;
import java.util.EnumMap;

public class Character
{
    private String name;

    public int armorClass;
    public int hitPoints;
    public int speed;
    
    private Map<DND.Attribute, Integer> attributes = new EnumMap<>(DND.Attribute.class);
    private Map<DND.Skill, Boolean> skillProficiencies = new EnumMap<>(DND.Skill.class);

    public int getAttribute(DND.Attribute attr)
    {
        return attributes.get(attr);
    }
    
    public void setAttribute(DND.Attribute attr, int val)
    {
        System.out.println("Setting attribute " + attr + " to " + val);
        attributes.put(attr, val);
    }

    public boolean getProficiency(DND.Skill skill)
    {
        return skillProficiencies.get(skill);
    }
    
    public void setProficiency(DND.Skill skill, boolean condition)
    {
        System.out.println("Setting proficiency of " + skill + " to " + condition);
        skillProficiencies.put(skill, condition);
    }

    public String getName()
    {
        return name;
    }

    Character(String name)
    {
        System.out.println("[" + name + "] Character constructor called");

        this.name = name;

        for(DND.Attribute attr : DND.Attribute.values())
        {
            setAttribute(attr, 0);
        }

        for(DND.Skill skill : DND.Skill.values())
        {
            setProficiency(skill, false);
        }
        System.out.println("[" + name + "] Character constructor finished");
    }
}