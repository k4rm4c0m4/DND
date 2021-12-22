/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dnd;

import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;

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

    public static Attribute getSkillAttribute(Skill skill)
    {
        return skillAttributeMap.get(skill);
    }
}