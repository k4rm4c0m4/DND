package uitest;

import dnd.*;
import dnd.Character;

class Main
{
    public static void main(String args)
    {
        PlayerCharacter simon = new PlayerCharacter("Simon");
        simon.setAttribute(DND.Attribute.INTELLIGENCE, 10);
        simon.setProficiency(DND.Skill.PERCEPTION, true);
        simon.getSkillValue(DND.Skill.PERCEPTION);
    }
}