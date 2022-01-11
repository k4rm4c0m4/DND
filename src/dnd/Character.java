/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import java.util.Map;
import java.util.EnumMap;

public class Character implements java.io.Serializable
{
    private String name;

    public int armorClass;
    public int hitPoints;
    public int speed;

    private Map<DNDGame.Attribute, Integer> attributes = new EnumMap<>(DNDGame.Attribute.class);

    /* functions */


    /* getter and setter function */
    public int getAttribute(DNDGame.Attribute attribute) { return attributes.get(attribute); }
    public void setAttribute(DNDGame.Attribute attribute, int value) { attributes.put(attribute, value); }
    public String getName() { return name; }

    public Character(String name)
    {
        this.name = name;

        for(DNDGame.Attribute attribute : DNDGame.Attribute.values())
        {
            setAttribute(attribute, 0);
        }
    }
}