package dnd;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class MainFrame extends JFrame
{
    public static void main(String args[])
    {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        new MainFrame("Dungeons & Dragons Character Creation Utility");
    }

    MainFrame self;
    DNDGame dndgame;
    CharacterTabbedPane cTabbedPane;
    Map<Integer, Character> tabIndexCharacterMap;

    public MainFrame(String title)
    {
        super();
        self = this;
        dndgame = new DNDGame("");
        tabIndexCharacterMap = new HashMap<>();
        cTabbedPane = new CharacterTabbedPane(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 800));
        
        add(cTabbedPane);
        setVisible(true);
    }

    public void addCharacterTab()
    {
        UIManager.put("OptionPane.acceptButtonText", "Add character");
        UIManager.put("OptionPane.cancelButtonText", "Cancel");

        String charName = JOptionPane.showInputDialog(null, "What is the name of this player character?", "Create new player character", JOptionPane.INFORMATION_MESSAGE);

        if(charName != null)
        {
            int trigger = 0;

            if(dndgame.getPlayerByName(charName) != null)
            {
                trigger = JOptionPane.showConfirmDialog((Component) null, "Warning: Character \"" + charName + "\" already exists. Overwrite?",
                "alert", JOptionPane.OK_CANCEL_OPTION);
            }

            if(trigger == 0)
            {
                dndgame.putPlayerCharacter(new PlayerCharacter(charName, 2));
            }
            loadCharacterTabs();
        }
    }

    public void loadCharacterTabs()
    {
        tabIndexCharacterMap.clear();

        while(cTabbedPane.getTabCount() > 1)
            cTabbedPane.remove(1);

        for(int i = 0; i < dndgame.playerCharacterMapAsList().size(); i++)
        {
            String charName = dndgame.playerCharacterMapAsList().get(i).getName();

            JLabel tabTitleLabel = new JLabel(charName);
            JTextArea textArea = new JTextArea();

            cTabbedPane.addTab(charName, textArea);
            cTabbedPane.setTabComponentAt(cTabbedPane.getTabCount() - 1, tabTitleLabel);

            tabIndexCharacterMap.put(cTabbedPane.getTabCount() - 1, dndgame.getPlayerByName(charName));
        }
    }

    public void removeCharacterTab(int index)
    {
        dndgame.removePlayerCharacterThroughName(tabIndexCharacterMap.get(index).getName());
        loadCharacterTabs();
    }

    public <K, V> K getKey(Map<K, V> map, V value) {
        for (Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}