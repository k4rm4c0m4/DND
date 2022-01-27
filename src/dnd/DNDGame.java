/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.io.*;

public class DNDGame implements java.io.Serializable
{
    /* Attribute enumeration denotes the different attributes a character has have. */
    public enum Attribute
    {
        STRENGTH,
        DEXTERITY,
        CONSTITUTION,
        INTELLIGENCE,
        WISDOM,
        CHARISMA,
    };
    
    /* Skill enumeration denotes the different skills a player character has. */
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

    /* Alignment enumeration denotes the different alignments a player character can have. */
    public enum Alignment
    {
        LAWFUL_GOOD,
        NEUTRAL_GOOD,
        CHAOTIC_GOOD,
        LAWFUL_NEUTRAL,
        TRUE_NEUTRAL,
        CHAOTIC_NEUTRAL,
        LAWFUL_EVIL,
        NEUTRAL_EVIL,
        CHAOTIC_EVIL
    }

    /* Name of the game and getter/setter */
    private String gameName;
        public String getGameName()
            { return gameName; }
        public void setGameName(String gameName)
            { this.gameName = gameName; }

    /* The id of this save */
    private int saveId;
        public int getSaveId()
            { return saveId; }

    /* A hashmap of all non player characters */
    private Map<String, NonPlayerCharacter> nonPlayerCharacters = new HashMap<>();
        public NonPlayerCharacter getNpcByName(String name)
            { return nonPlayerCharacters.get(name); }
        public void putNonPlayerCharacter(NonPlayerCharacter pc)
            { nonPlayerCharacters.put(pc.getName(), pc); }
        public void removeNonPlayerCharacterThroughName(String name)
            { nonPlayerCharacters.remove(name); }
        public List<PlayerCharacter> nonPlayerCharacterMapAsList()
            { return new ArrayList<PlayerCharacter>(playerCharacters.values()); }
        
        
    /* A hashmap of all player characters */
    private Map<String, PlayerCharacter> playerCharacters = new HashMap<>();
        public PlayerCharacter getPlayerByName(String name)
            { return playerCharacters.get(name); }
        public void putPlayerCharacter(PlayerCharacter pc)
            { playerCharacters.put(pc.getName(), pc); }
        public void removePlayerCharacterThroughName(String name)
            { playerCharacters.remove(name); }
        public List<PlayerCharacter> playerCharacterMapAsList()
            { return new ArrayList<PlayerCharacter>(playerCharacters.values()); }

    /* A map of all skills to their respective attributes */
    private static Map<Skill, Attribute> skillAttributesMap = new EnumMap<>(Skill.class);
        public static Attribute getSkillAttribute(Skill skill)
            { return skillAttributesMap.get(skill); }
        static
        {
            skillAttributesMap.put(Skill.ACROBATICS, Attribute.DEXTERITY);
            skillAttributesMap.put(Skill.ANIMAL_HANDLING, Attribute.WISDOM);
            skillAttributesMap.put(Skill.ARCANA, Attribute.INTELLIGENCE);
            skillAttributesMap.put(Skill.ATHLETICS, Attribute.STRENGTH);
            skillAttributesMap.put(Skill.DECEPTION, Attribute.CHARISMA);
            skillAttributesMap.put(Skill.HISTORY, Attribute.INTELLIGENCE);
            skillAttributesMap.put(Skill.INSIGHT, Attribute.WISDOM);
            skillAttributesMap.put(Skill.INTIMIDATION, Attribute.CHARISMA);
            skillAttributesMap.put(Skill.INVESTIGATION, Attribute.INTELLIGENCE);
            skillAttributesMap.put(Skill.MEDICINE, Attribute.WISDOM);
            skillAttributesMap.put(Skill.NATURE, Attribute.INTELLIGENCE);
            skillAttributesMap.put(Skill.PERCEPTION, Attribute.WISDOM);
            skillAttributesMap.put(Skill.PERFORMANCE, Attribute.CHARISMA);
            skillAttributesMap.put(Skill.PERSUASION, Attribute.CHARISMA);
            skillAttributesMap.put(Skill.RELIGION, Attribute.INTELLIGENCE);
            skillAttributesMap.put(Skill.SLEIGHT_OF_HAND, Attribute.DEXTERITY);
            skillAttributesMap.put(Skill.STEALTH, Attribute.DEXTERITY);
            skillAttributesMap.put(Skill.SURVIVAL, Attribute.WISDOM);
        }

    /* Function that returns the sum of 'dice' amounts of ((randoms with the upperbound 'sides') + 1) */
    public static int rollDice(int sides, int dice)
    {
        Random random = new Random();
        int _return = 0;

        for(int i = 0; i < dice; i++)
        {
            _return += random.nextInt(sides) + 1;
        }

        return _return;
    }

    /* Saves game state to file */
    public Integer saveGameStateToFile(String dir)
    {
        saveId++;

        try {
            FileOutputStream fileOut = new FileOutputStream(dir);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Gamestate was saved in \'" + dir + "\'.");
        }
        catch (IOException i)
        {
            i.printStackTrace();
            System.out.println("Could not save gamestate in \'" + dir + "\'. Refer to stack trace above.");
        }

        return saveId;
    }

    /* Loaf game state from file */
    public static DNDGame loadGameStateFromFile(String dir)
    {
        DNDGame _return;

        try {
            FileInputStream fileIn = new FileInputStream(dir);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            _return = (DNDGame) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Could not load gamestate from \'" + dir + "\'. Refer to stack trace above.");
            _return = null;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            System.out.println("Could not load gamestate from \'" + dir + "\': Class not found exception. Refer to stack trace above.");
            _return = null;
        }

        return _return;
    }

    /* Constructor */
    DNDGame(String gameName)
    {
        saveId = 0;
        this.gameName = gameName;
    }
}