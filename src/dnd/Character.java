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
        attributes.put(attr, val);
    }
    
    Character(String name)
    {
        this.name = name;
        
        for(DND.Attribute attr : DND.Attribute.values())
        {
            setAttribute(attr, 0);
            System.out.println("Put 0 on " + attr);
        }
    }
}