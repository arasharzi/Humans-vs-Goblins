/*
    yup i'm treating treasure as an entity :P
 */
package entities;

import items.Inventory;

public class Treasure extends Entity
{


    public Treasure()
    {
        this.setName("Treasure Chest");
        this.setInventory(new Inventory());
    }



    @Override
    public String toString()
    {
        return "I'm a treasure chest";
    }
}
