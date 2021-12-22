/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import dnd.DND.Attribute;
import dnd.DND.Skill;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("## Dungeons and Dragons Java Program");
        System.out.println("------------------------------------");
        PlayerCharacter simon = new PlayerCharacter("Simon");
        simon.setAttribute(Attribute.STRENGTH, 15);
        simon.setProficiency(Skill.ATHLETICS, false);
        System.out.println(simon.getSkillValue(Skill.ATHLETICS));
        System.out.println(simon.skill_roll(Skill.ATHLETICS));
    }
}