package uitest;

import dnd.*;

class Main
{
    public static void main(String[] args)
    {
        PlayerCharacter simon = new PlayerCharacter("Simon");
        simon.setAttribute(DND.Attribute.WISDOM, 20);
        simon.setProficiency(DND.Skill.PERCEPTION, true);
        System.out.println(simon.skill_roll(DND.Skill.PERCEPTION));
    }
}