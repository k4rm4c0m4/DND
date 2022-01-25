package dnd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Container implements Serializable
{
    List<Item> items = new ArrayList<>();

    int maxSlots;
    int slots;    

    public int get_max_slots() { return maxSlots; }
    public int get_slots() { return slots; }

    public int addItem(Item item)
    {
        int newslots = slots + item.getSlots();

        if(newslots > maxSlots && !(maxSlots < 0))
        {
            System.out.println("ERROR: Did not add item " + item.getName() + " to container. No slots available.");
        }
        else
        {
            items.add(item);
            slots = newslots;
        }

        return slots;
    }

    public List<Item> getItems() { return items; }
    public int getItemsSize() { return items.size(); }
    public Item getItemByIndex(int index) { return items.get(index); }

    public Item getItemByName(String itemname)
    {
        for(int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getName() == itemname)
            {
                return items.get(i);
            }
            else
            {
                System.err.println("ERROR: Could not retrieve item with name " + itemname + ". Returned null.");
            }

        }

        return null;
    }

    public Item removeItemByIndex(int index)
    {
        Item item = items.get(index);
        items.remove(index);
        return item;
    }

    public Item removeItemByName(String itemname)
    {
        Item item = null;

        for(int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getName() == itemname)
            {
                item = items.get(i);
                items.remove(i);
            }
            else
            {
                System.err.println("ERROR: Could not remove item with name " + itemname + ". Returned null.");
            }
        }

        return item;
    }

    Container(int maxSlots) { this.maxSlots = maxSlots; }
}