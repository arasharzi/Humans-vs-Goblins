/*

 */
package map;


import entities.*;

import java.util.Random;

public class GameMap
{
    private Location human;
    private Location goblin;
    private Location treasure;
    private int x;
    private int y;

    public GameMap(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setHuman(Location human)
    {
        this.human = human;
    }

    public void setGoblin(Location goblin)
    {
        this.goblin = goblin;
    }

    public void setTreasure(Location treasure)
    {
        this.treasure = treasure;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        StringBuilder map = new StringBuilder();

        for(int i = 0; i < this.y; i++)
        {
            for(int j = 0; j < this.x; j++)
            {
                if (human.getX() == j && human.getY() == i)
                {
                    map.append("X");
                }
                else if (goblin.getX() == j && goblin.getY() == i)
                {
                    map.append("G");
                }
                else if (treasure.getX() == j && treasure.getY() == i)
                {
                    map.append("T");
                }
                else
                {
                    map.append("*");
                }
            }
            map.append("\n");
        }
        return map.toString();
    }
}
