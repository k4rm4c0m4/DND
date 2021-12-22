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

    /* getter and setter function */
    public int getAttribute(DND.Attribute attribute) { return attributes.get(attribute); }
    public void setAttribute(DND.Attribute attribute, int value) { attributes.put(attribute, value); }
    public String getName() { return name; }

    Character(String name)
    {
        this.name = name;

        for(DND.Attribute attribute : DND.Attribute.values())
        {
            setAttribute(attribute, 0);
        }
    }
}