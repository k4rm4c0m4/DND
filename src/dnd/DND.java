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
    public static Map<Skill, Attribute> skillAttributeMap = new EnumMap<>(Skill.class);
}