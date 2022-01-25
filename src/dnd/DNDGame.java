/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.io.*;

public class DNDGame implements java.io.Serializable
{
    public enum Attribute
    {
        STRENGTH,
        DEXTERITY,
        CONSTITUTION,
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

    private String gameName;
    public void setGameName(String gameName) { this.gameName = gameName; }
    private Integer saveid;

    private Map<String, NonPlayerCharacter> non_playser_characters = new HashMap<>();
    private Map<String, PlayerCharacter> player_characters = new HashMap<>();

    private static Map<Skill, Attribute> skill_attributes_map = new EnumMap<>(Skill.class);

    /* Mapping of all skill enums to their attribute enum */
    static
    {
        skill_attributes_map.put(Skill.ACROBATICS, Attribute.DEXTERITY);
        skill_attributes_map.put(Skill.ANIMAL_HANDLING, Attribute.WISDOM);
        skill_attributes_map.put(Skill.ARCANA, Attribute.INTELLIGENCE);
        skill_attributes_map.put(Skill.ATHLETICS, Attribute.STRENGTH);
        skill_attributes_map.put(Skill.DECEPTION, Attribute.CHARISMA);
        skill_attributes_map.put(Skill.HISTORY, Attribute.INTELLIGENCE);
        skill_attributes_map.put(Skill.INSIGHT, Attribute.WISDOM);
        skill_attributes_map.put(Skill.INTIMIDATION, Attribute.CHARISMA);
        skill_attributes_map.put(Skill.INVESTIGATION, Attribute.INTELLIGENCE);
        skill_attributes_map.put(Skill.MEDICINE, Attribute.WISDOM);
        skill_attributes_map.put(Skill.NATURE, Attribute.INTELLIGENCE);
        skill_attributes_map.put(Skill.PERCEPTION, Attribute.WISDOM);
        skill_attributes_map.put(Skill.PERFORMANCE, Attribute.CHARISMA);
        skill_attributes_map.put(Skill.PERSUASION, Attribute.CHARISMA);
        skill_attributes_map.put(Skill.RELIGION, Attribute.INTELLIGENCE);
        skill_attributes_map.put(Skill.SLEIGHT_OF_HAND, Attribute.DEXTERITY);
        skill_attributes_map.put(Skill.STEALTH, Attribute.DEXTERITY);
        skill_attributes_map.put(Skill.SURVIVAL, Attribute.WISDOM);
    }

    /* functions */
    public static int roll_dice(int sides, int dice)
    {
        Random random = new Random();
        int _return = 0;

        for(int i = 0; i < dice; i++)
        {
            _return *= random.nextInt(sides) + 1;
        }

        return _return;
    }


    public Integer saveGameStateToFile()
    {
        String userHome = System.getProperty("user.home");
        String dir = userHome + "/documents/DNDGame/" + gameName + "/";

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

    public static DNDGame loadGameStateFromFile(String gameName, Integer saveid)
    {
        String userHome = System.getProperty("user.home");
        String dir = userHome + "/documents/DNDGame/" + gameName + "/";
        DNDGame gamestate = null;

        try {
            FileInputStream fileIn = new FileInputStream(dir + saveid.toString() + ".dndstate");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gamestate = (DNDGame) in.readObject();
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

    public static DNDGame loadGameStateFromFile(String gameName)
    {
        System.out.println("Choosing newest save from " + gameName);

        String userHome = System.getProperty("user.home");
        String dir = userHome + "/documents/DNDGame/" + gameName + "/";
        DNDGame gamestate = null;

        //Creating a File object for directory
        File directoryPath = new File(dir);

        if(!directoryPath.isDirectory())
        {
            System.err.println(directoryPath.getAbsolutePath() + "/ is not a directory.");
            return null;
        }

        //List of all files and directories
        List<String> fileNameList = Arrays.asList(directoryPath.list());
        Collections.sort(fileNameList);

        if(fileNameList.size() < 1)
        {
            System.err.println("No save found.");
            return null;
        }

        System.out.println("Sorted list of saves of " + gameName);
        for(String fileName : fileNameList)
        {
            System.out.println("  " + fileName);
        }
        System.out.println("----");
        String newestSave = fileNameList.get(fileNameList.size() - 1);
        try {
            FileInputStream fileIn = new FileInputStream(dir + newestSave);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gamestate = (DNDGame) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("No file found");
            c.printStackTrace();
        }

        return gamestate;
    }

    public static void deleteGameStates(String gameName)
    {

        String userHome = System.getProperty("user.home");
        String dir = userHome + "/documents/DNDGame/" + gameName + "/";
        System.out.println("WARNING: Deleting directory " + dir);

        // creating a File object for directory
        File directoryPath = new File(dir);

        // guard clause that returns if directory path is not a directory
        if(!directoryPath.isDirectory())
        {
            System.out.println("Error: '" + dir + "' is not a directory.");
            return;
        }

        deleteDirectory(directoryPath);
    }

    /** function to delete subdirectories and files.
     *  DANGEROUS! Never use except when necessary.
     */
    private static void deleteDirectory(File file)
    {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : file.listFiles()) {
  
            // if it is a subfolder,e.g Rohan and Ritik,
            // recursiley call function to empty subfolder
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }
  
            // delete files and empty subfolders
            subfile.delete();
        }
    }


    /* Getter and setter functions */
    public String getGameName()
    { 
        return gameName;
    }

    public NonPlayerCharacter getNpcByName(String name)
    {
        return non_playser_characters.get(name);
    }

    public PlayerCharacter getPlayerByName(String name)
    {
        return player_characters.get(name);
    }

    public void putPlayerCharacter(PlayerCharacter pc)
    {
        player_characters.put(pc.getName(), pc);
    }

    public void putNonPlayerCharacter(NonPlayerCharacter pc)
    {
        non_playser_characters.put(pc.getName(), pc);
    }

    public static Attribute getSkillAttribute(Skill skill)
    {
        return skill_attributes_map.get(skill);
    }

    /* Constructor */
    DNDGame(String gameName)
    {
        saveid = 0;
        this.gameName = gameName;
        saveGameStateToFile();
    }
}