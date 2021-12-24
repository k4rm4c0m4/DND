/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.io.*;

public class DND implements java.io.Serializable
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

    private String gameName;
    private Integer saveid;

    private Map<String, NonPlayerCharacter> npcs = new HashMap<>();
    private Map<String, PlayerCharacter> players = new HashMap<>();
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


    public Integer saveGameStateToFile()
    {
        String userHome = System.getProperty("user.home");
        String dir = userHome + "/Documents/DND/" + gameName + "/";

        File dirpath = new File(dir);

        if(!dirpath.isDirectory() && !dirpath.exists())
        {
            dirpath.mkdir();
        }

        saveid++;
        
        try {
           FileOutputStream fileOut = new FileOutputStream(dir + saveid.toString() + ".dndstate");
           ObjectOutputStream out = new ObjectOutputStream(fileOut);
           out.writeObject(this);
           out.close();
           fileOut.close();
           System.out.println("Gamestate saved in " + dir + saveid.toString() + ".dndstate");
        } catch (IOException i) {
           i.printStackTrace();
        }

        return saveid;
    }

    /* not adjusted to new saveGameStateToFile function */
    public static DND loadGameStateFromFile(String gameName, Integer saveid)
    {
        String userHome = System.getProperty("user.home");
        String dir = userHome + "/Documents/DND/" + gameName + "/";
        DND gamestate = null;

        try {
            FileInputStream fileIn = new FileInputStream(dir + saveid.toString() + ".dndstate");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gamestate = (DND) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("File not found");
            c.printStackTrace();
            return null;
        }

        return gamestate;
    }

    /* not implemented */
    public static DND loadGameStateFromFile(String gameName)
    {
        return null;
    }

    /* Getter and setter functions */
    public String getGameName()
    { 
        return gameName;
    }

    public NonPlayerCharacter getNpcByName(String name)
    {
        return npcs.get(name);
    }

    public PlayerCharacter getPlayerByName(String name)
    {
        return players.get(name);
    }

    public void putPlayerCharacter(PlayerCharacter pc)
    {
        players.put(pc.getName(), pc);
    }

    public void putNonPlayerCharacter(NonPlayerCharacter pc)
    {
        npcs.put(pc.getName(), pc);
    }

    public static Attribute getSkillAttribute(Skill skill)
    {
        return skillAttributeMap.get(skill);
    }

    /* Constructor */
    DND(String gameName)
    {
        saveid = 0;
        this.gameName = gameName;
    }
}