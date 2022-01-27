/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import java.util.Map;
import java.util.EnumMap;


public class Character implements java.io.Serializable
{
    private String name;
        public String getName()
            { return name; }
        public void setName(String name)
            { this.name = name; }

    private Container hands;
        public Container getHands()
            { return hands; }

    private DNDGame.Alignment al;
        public DNDGame.Alignment getAlignment()
            { return al; }
        public void setAlignment(DNDGame.Alignment al)
            { this.al = al; }

    private String dndclass;
        public void setDndClass(String dndclass)
            { this.dndclass = dndclass; }
        public String getDndClass()
            { return dndclass; }

    private int level;
        public void setLevel(int level)
            { this.level = level; }
        public int getLevel()
            { return level; }

    private int armorClass;
        public void setArmorClass(int ac)
            { this.armorClass = ac; }
        public int getArmorClass()
            { return armorClass; }

    private int hitPoints;
        public void setHitPoints(int hp)
            { this.hitPoints = hp; }
        public int getHitPoints()
            { return hitPoints; }
        public int subtractHp(int val)
        {
            int newhp = getHitPoints() - val;
            setHitPoints(newhp);
            return newhp;
        }
        public int healHp(int val)
        {
            int newhp = getHitPoints() - val;
            int maxhp = getTotalHitPoints() + getHitPointBonus();
        
            if(newhp > maxhp)
                newhp = maxhp;
    
            setHitPoints(newhp);
            return newhp;
        }
        
    private int totalHitPoints;
        public void setTotalHitPoints(int totalHp)
            { this.totalHitPoints = totalHp; }
        public int getTotalHitPoints()
            { return totalHitPoints; }

    private int hitPointBonus;
        public void setHitPointBonus(int hpBonus )
            { this.hitPointBonus = hpBonus; }
        public int getHitPointBonus()
            { return hitPointBonus; }

    private int flyingSpeed;
        public void setFlyingSpeed(int flyingSpeed)
            { this.flyingSpeed = flyingSpeed; }
        public int getFlyingSpeed()
            { return flyingSpeed; }

    private int walkingSpeed;
        public void setWalkingSpeed(int walkingSpeed)
            { this.walkingSpeed = walkingSpeed; }
        public int getWalkingSpeed()
            { return walkingSpeed; }

    private Map<DNDGame.Attribute, Integer> attributes = new EnumMap<>(DNDGame.Attribute.class);
        public int getAttributeVal(DNDGame.Attribute attribute)
            { return attributes.get(attribute); }
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
        public int getAttributeModifier(DNDGame.Attribute attribute)
        {
            int _return = getAttributeVal(attribute);
            _return -= 10;
            _return /= 2;
            return _return;
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