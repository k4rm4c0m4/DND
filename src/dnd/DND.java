/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DND
{
    public enum Attribute
    {
        STRENGTH,
        DEXTERITY,
        CONSTUTIUTION,
        INTELLIGENCE,
        WISDOM,
        CHARISMA,
    };
    
    public enum Skill
    {
        ACROBATICS,
        ANIMAL_HANDLING,
        ARCANA,
        ATHLETICS,
        DECEPTION,
        HISTORY,
        INSIGHT,
        INTIMIDATION,
        INVESTIGATION,
        MEDICINE,
        NATURE,
        PERCEPTION,
        PERFORMANCE,
        PERSUASION,
        RELIGION,
        SLEIGHT_OF_HAND,
        STEALTH,
        SURVIVAL
    }

    Map<String, NonPlayerCharacter> monsters = new HashMap<>();
    Map<String, PlayerCharacter> players = new HashMap<>();
    private static Map<Skill, Attribute> skillAttributeMap;

    /* Mapping of all skill enums to their attribute enum */
    static
    {
        skillAttributeMap = new EnumMap<>(Skill.class);
        skillAttributeMap.put(Skill.ACROBATICS, Attribute.DEXTERITY);
        skillAttributeMap.put(Skill.ANIMAL_HANDLING, Attribute.WISDOM);
        skillAttributeMap.put(Skill.ARCANA, Attribute.INTELLIGENCE);
        skillAttributeMap.put(Skill.ATHLETICS, Attribute.STRENGTH);
        skillAttributeMap.put(Skill.DECEPTION, Attribute.CHARISMA);
        skillAttributeMap.put(Skill.HISTORY, Attribute.INTELLIGENCE);
        skillAttributeMap.put(Skill.INSIGHT, Attribute.WISDOM);
        skillAttributeMap.put(Skill.INTIMIDATION, Attribute.CHARISMA);
        skillAttributeMap.put(Skill.INVESTIGATION, Attribute.INTELLIGENCE);
        skillAttributeMap.put(Skill.MEDICINE, Attribute.WISDOM);
        skillAttributeMap.put(Skill.NATURE, Attribute.INTELLIGENCE);
        skillAttributeMap.put(Skill.PERCEPTION, Attribute.WISDOM);
        skillAttributeMap.put(Skill.PERFORMANCE, Attribute.CHARISMA);
        skillAttributeMap.put(Skill.PERSUASION, Attribute.CHARISMA);
        skillAttributeMap.put(Skill.RELIGION, Attribute.INTELLIGENCE);
        skillAttributeMap.put(Skill.SLEIGHT_OF_HAND, Attribute.DEXTERITY);
        skillAttributeMap.put(Skill.STEALTH, Attribute.DEXTERITY);
        skillAttributeMap.put(Skill.SURVIVAL, Attribute.WISDOM);
    }

    /* functions */
    public static int rollDX(int x)
    {
        Random random = new Random();
        return random.nextInt(x) + 1;
    }

    public static Attribute getSkillAttribute(Skill skill)
    {
        return skillAttributeMap.get(skill);
    }
}