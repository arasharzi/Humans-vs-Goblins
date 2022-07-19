/*

 */
package entities;

import items.Inventory;
import map.Location;

public class Goblin extends Entity
{
    public Goblin()
    {
        this.setName("Goblin");
        this.setHealth(10);
        this.setStrength(2);
        this.setDexterity(2);
        this.setLuck(1);
        this.setArmor(1);
        this.setInventory(new Inventory());
    }

    public Goblin (String name, int level, int health, int strength, int dexterity, int luck,
                   int armor, Location location)
    {
        this.setName(name);
        this.setHealth(health);
        this.setStrength(strength);
        this.setDexterity(dexterity);
        this.setLuck(luck);
        this.setArmor(armor);
        this.setLocation(location);
        this.setInventory(new Inventory());
    }

    public String attack(Entity entity)
    {
        if (entity.getDexterity() / 10.0 >= Math.random())
        {
            return "You dodge the " + this.getName() + "'s attack.";
        }

        double hit = this.getStrength();
        if (this.getLuck() / 10.0 >= Math.random())
        {
            hit *= 2;
        }
        int damage = entity.getArmor() - (int)hit;

        if (damage == 0)
        {
            entity.setArmor(0);
            return "The " + this.getName() + " broke your armor.";
        }
        else if (damage < 0)
        {
            entity.setArmor(0);
            entity.setHealth(entity.getHealth() + damage);
            return "The " + this.getName() + " did " + Math.abs(damage) + " damage to you.";
        }
        else
        {
            entity.setArmor(entity.getArmor() - damage);
            return "The " + this.getName() + " did " + damage + " damage to your armor.";
        }
    }

    @Override
    public String toString()
    {
        return "I am a goblin";
    }
}
