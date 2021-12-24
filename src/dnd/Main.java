/**
 * (c) 2021 Simon Busch, Maurice Bonke & Mikail Yangin under the GNU General Purpose License v3.0
 */

package dnd;

import dnd.DND.Attribute;
import dnd.DND.Skill;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("## Dungeons and Dragons Java Program");
        System.out.println("------------------------------------");
        DND game = new DND("thisGame");
        game.putPlayerCharacter(new PlayerCharacter("Simon"));
        game.getPlayerByName("Simon").setAttribute(Attribute.STRENGTH, 15);
        game.getPlayerByName("Simon").setProficiency(Skill.ATHLETICS, false);

        int i = game.saveGameStateToFile();

        DND deserialisedGameState = DND.loadGameStateFromFile("thisGame", i);
        System.out.println(deserialisedGameState.getPlayerByName("Simon").getProficiency(Skill.ATHLETICS));
        System.out.println(deserialisedGameState.getGameName());
    }
}