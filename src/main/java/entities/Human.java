/*

 */
package entities;

import items.Inventory;
import map.Location;

public class Human extends Entity
{
    public Human()
    {
        this.setName("Human");
        this.setHealth(10);
        this.setStrength(3);
        this.setDexterity(1);
        this.setLuck(4);
        this.setArmor(2);
        this.setInventory(new Inventory());

    }
    public Human (String name, int level, int health, int strength, int dexterity, int luck,
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
            return entity.getName() + " dodges your attack.";
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
            return "You broke the " + entity.getName() + "'s armor.";
        }
        else if (damage < 0)
        {
            entity.setArmor(0);
            entity.setHealth(entity.getHealth() + damage);
            return "You did " + Math.abs(damage) + " damage to the " + entity.getName();
        }
        else
        {
            entity.setArmor(entity.getArmor() - damage);
            return "You did " + damage + " damage to the " + entity.getName() + "'s armor.";
        }
    }

    @Override
    public String toString()
    {
        return "I am a human";
    }
}
