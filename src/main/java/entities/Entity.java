/*

 */
package entities;


import items.Inventory;
import map.Location;

public abstract class Entity
{
    private String name;
    private int health;
    private int strength;
    private int dexterity;
    private int luck;
    private int armor;

    private Inventory inventory;

    private Location location;


    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public int getHealth()
    {
        return health;
    }
    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getStrength()
    {
        return strength;
    }
    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getDexterity()
    {
        return dexterity;
    }
    public void setDexterity(int dexterity)
    {
        this.dexterity = dexterity;
    }

    public int getLuck()
    {
        return luck;
    }
    public void setLuck(int luck)
    {
        this.luck = luck;
    }

    public int getArmor()
    {
        return armor;
    }
    public void setArmor(int armor)
    {
        this.armor = armor;
    }

    public Inventory getInventory()
    {
        return inventory;
    }
    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }

    public Location getLocation()
    {
        return location;
    }
    public void setLocation(Location location)
    {
        this.location = location;
    }


    public String attack(Entity entity)
    {
        return "";
    }

    @Override
    public String toString()
    {
        return "I am a basic entity.";
    }

}
