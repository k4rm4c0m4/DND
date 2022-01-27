package dnd;

import java.io.Serializable;

public class Item implements Serializable
{
    private String name;
        public String getName()
            { return name; }
        public void setName(String name)
            { this.name = name; }

    private int slots;
        public int getSlots()
            { return slots; }

    private int weight;
        public int getWeight()
            { return weight; }
        public void setWeight(int weight)
            { this.weight = weight; }

    Item(String name, int slots, int weight)
    {
        this.name = name;
        this.slots = slots;
        this.weight = weight;
    }
}
