/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import java.util.Map;
import java.util.EnumMap;

public class Character implements java.io.Serializable
{
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    private Container hands;
    public Container getHands() { return hands; }

    private DNDGame.Alignment al;
    public DNDGame.Alignment getAlignment() { return al; }
    public void setAlignment(DNDGame.Alignment al) { this.al = al; }

    private int level;
    public void setLevel(int level) { this.level = level; }
    public int getLevel() { return level; }

    private int armor_class;
    public void setArmorClass(int ac) { this.armor_class = ac; }
    public int getArmorClass() { return armor_class; }

    private int hit_points;
    public void setHitPoints(int hp) { this.hit_points = hp; }
    public int getHitPoints() { return hit_points; }

    private int total_hit_points;
    public void setTotalHitPoints(int total_hp) { this.total_hit_points = total_hp; }
    public int getTotalHitPoints() { return total_hit_points; }

    private int hit_point_bonus;
    public void setHitPointBonus(int hp_bonus ) { this.hit_point_bonus = hp_bonus; }
    public int getHitPointBonus() { return hit_point_bonus; }

    private int flying_speed;
    public void setFlyingSpeed(int flying_speed) { this.flying_speed = flying_speed; }
    public int getFlyingSpeed() { return flying_speed; }

    private int walking_speed;
    public void setWalkingSpeed(int walking_speed) { this.walking_speed = walking_speed; }
    public int getWalkingSpeed() { return walking_speed; }

    private Map<DNDGame.Attribute, Integer> attributes = new EnumMap<>(DNDGame.Attribute.class);
    public int getAttributeVal(DNDGame.Attribute attribute) { return attributes.get(attribute); }
    public void setAttributeVal(DNDGame.Attribute attribute, int val)
    {
        if(val < 4 || val > 20)
        {
            System.out.println("ERROR: Did not set attribute " + attribute + " to " + val + " because attributes must be >=4 or <=20.");
        }
        else
        {
            attributes.put(attribute, val);
        }
    }

    private String dndclass;
    public void setDndClass(String dndclass) { this.dndclass = dndclass; }
    public String getDndClass() { return dndclass; }

    public int subtract_hp(int val)
    {
        int newhp = getHitPoints() - val;
        setHitPoints(newhp);
        return newhp;
    }

    public int heal_hp(int val)
    {
        int newhp = getHitPoints() - val;
        int maxhp = getTotalHitPoints() + getHitPointBonus();
    
        if(newhp > maxhp)
            newhp = maxhp;

        setHitPoints(newhp);
        return newhp;
    }

    Character(String name, int handcount)
    {
        this.name = name;

        hands = new Container(handcount);

        for(DNDGame.Attribute attribute : DNDGame.Attribute.values())
        {
            setAttributeVal(attribute, 4);
        }
    }
}