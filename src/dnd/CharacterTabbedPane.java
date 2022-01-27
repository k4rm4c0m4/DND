package dnd;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CharacterTabbedPane extends JTabbedPane {
    MainFrame parent;

    CharacterTabbedPane(MainFrame parent)
    {
        super();

        this.parent = parent;

        setFocusable(false);
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) { }

            @Override
            public void mouseEntered(MouseEvent arg0) { }

            @Override
            public void mouseExited(MouseEvent arg0) { }

            @Override
            public void mousePressed(MouseEvent e) {

                int index = getSelectedIndex();

                if(SwingUtilities.isRightMouseButton(e) && index != 0)
                {
                    JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem delete = new JMenuItem("Remove Character");

                    delete.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            parent.removeCharacterTab(index);
                        }

                    });

                    popupMenu.add(delete);
                    popupMenu.show(parent, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent arg0) { }
        });

        JButton addButton = new JButton("Create New Character");
        addButton.setBorder(null);
        addButton.setFocusPainted(false);
        addButton.setContentAreaFilled(false);
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                parent.addCharacterTab();
            }

        });

        JTextPane initialContent = new JTextPane();
        initialContent.setEditable(false);
        initialContent.setBorder(null);
        initialContent.setContentType( "text/html" );    
        initialContent.setText( "<html><body><h1>Dungeons & Dragons Character Creation Utility</h1><p>This program, the D&DCCU is a simple java application built with swing that is supposed to model and organise D&D character sheets, both for NPCs and PCs.</p><p>To create a new character, simply click the \"Create new character\" button in the top left corner.</p><p>Happy creating!</p></body></html>" );
        addTab("+", initialContent);
        setTabComponentAt(0, addButton);
    }
}