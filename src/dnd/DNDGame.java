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
        String dir = userHome + "/Documents/DNDGame/" + gameName + "/";

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
        String dir = userHome + "/Documents/DNDGame/" + gameName + "/";
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
        String dir = userHome + "/Documents/DNDGame/" + gameName + "/";
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
        String dir = userHome + "/Documents/DNDGame/" + gameName + "/";
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

    // function to delete subdirectories and files
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
    DNDGame(String gameName)
    {
        saveid = 0;
        this.gameName = gameName;
        saveGameStateToFile();
    }
}