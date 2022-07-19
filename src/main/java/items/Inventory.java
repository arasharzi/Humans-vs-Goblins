/*

 */
package items;


import java.util.ArrayList;

public class Inventory
{
    ArrayList<String> inventory;
    public Inventory()
    {
        inventory = new ArrayList<>();
    }

    public int getSize()
    {
        return inventory.size();
    }

    public void addItem(String item)
    {
        if (!inventory.contains(item))
        {
            inventory.add(item);
        }
    }

    public void removeItem(int item)
    {
            inventory.remove(item);
    }

    public String getDrop()
    {
        return inventory.get(0);
    }

    public String itemToString(int index)
    {
        return inventory.get(index);
    }

    @Override
    public String toString()
    {
        return this.inventory.toString();
    }
}
