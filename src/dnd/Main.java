/**
 * (c) 2021 Simon Busch
 */

package dnd;

import dnd.DND.Attribute;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("## Dungeons and Dragons Java Program");
        System.out.println("------------------------------------");
        Character simon = new Character("Simon");
        simon.setAttribute(Attribute.STRENGTH, 12);
    }
}