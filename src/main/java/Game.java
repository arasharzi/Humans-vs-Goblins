/*

 */


import entities.*;
import items.Item;
import items.armor.*;
import items.weapons.*;
import map.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game
{
    GameMap map;
    Human human;
    Goblin goblin;
    Treasure treasure;
    int xmap;
    int ymap;
    int round = 1;
    ArrayList<Item> items = new ArrayList<>();

    public Game()
    {
        this.xmap = 20;
        this.ymap = 8;
    }

    public void initialize()
    {
        map = new GameMap(xmap, ymap);
        human = new Human();
        goblin = new Goblin();
        treasure = new Treasure();

        items.add(new Dagger());
        items.add(new Mace());
        items.add(new Sword());
        items.add(new Leather());
        items.add(new Plate());
        items.add(new Studded());

        // set up inventories for entities
        goblin.getInventory().addItem(items.get(new Random().nextInt(items.size() - 1)).getName());
        treasure.getInventory().addItem(items.get(new Random().nextInt(items.size() - 1)).getName());

        // set up entity map locations.
        human.setLocation(randomLocation());
        goblin.setLocation(randomLocation());
        treasure.setLocation(randomLocation());
        map.setHuman(human.getLocation());
        map.setGoblin(goblin.getLocation());
        map.setTreasure(treasure.getLocation());

    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try
        {

            System.out.println("Please enter a name:");
            human.setName(in.nextLine().trim());
        }
        catch (Exception e)
        {
            System.out.println("Invalid entry");
        }

        System.out.println("Hi " + human.getName() + " lets play a game!");
        System.out.println("Collect the treasure and kill the goblin!");

        while(true)
        {
            System.out.println(map.toString());
            System.out.println("Inventory:" + human.getInventory().toString() + " Round: " + round);
            getMove();
            if (touching(goblin))
            {
                combat(human, goblin);
            }
            else if (touching(treasure))
            {
                if (treasure.getInventory().getSize() == 1)
                {
                    String drop = treasure.getInventory().toString();
                    System.out.println("The treasure chest contained a " + drop.substring(1, drop.length() - 1));
                    human.getInventory().addItem(treasure.getInventory().getDrop());
                    treasure.getInventory().removeItem(0);
                }
            }
            else
            {
                goblinMove();
                if (touching(goblin))
                {
                    combat(goblin, human);
                }
            }
            if(human.getHealth() <= 0)
            {
                System.out.println("\nYou've been killed by the " + goblin.getName());
                break;
            }
            else if (goblin.getHealth() <= 0)
            {
                System.out.println(human.getName() + " you killed the " + goblin.getName() + "!\n");
                String drop = goblin.getInventory().toString();
                System.out.println("The goblin dropped a " + drop.substring(1, drop.length() - 1));
                human.getInventory().addItem(goblin.getInventory().getDrop());
                newMap();
            }
        }
    }

    private void newMap()
    {
        round++;
        try
        { TimeUnit.SECONDS.sleep(2); }
        catch (Exception e)
        { System.out.println(e.getMessage()); }

        map = new GameMap(xmap, ymap);
        goblin = new Goblin();
        treasure = new Treasure();

        human.setLocation(randomLocation());
        goblin.setLocation(randomLocation());
        treasure.setLocation(randomLocation());
        map.setHuman(human.getLocation());
        map.setGoblin(goblin.getLocation());
        map.setTreasure(treasure.getLocation());
    }

    private Location randomLocation()
    {
        Random random = new Random();
        return new Location(random.nextInt(this.xmap), random.nextInt(this.ymap));
    }

    private void getMove()
    {
        int x = human.getLocation().getX();
        int y = human.getLocation().getY();
        String input;
        Scanner in = new Scanner(System.in);

        while(true)
        {
            try
            {
                System.out.println("Select your next move. (n, s, e, w, or select i to equip an item.)");
                input = in.nextLine().toLowerCase().trim();

                switch (input)
                {
                    case "n" -> human.getLocation().setY(Math.max(0, y - 1));
                    case "s" -> human.getLocation().setY(Math.min(ymap - 1, y + 1));
                    case "e" -> human.getLocation().setX(Math.min(xmap - 1, x + 1));
                    case "w" -> human.getLocation().setX(Math.max(0, x - 1));
                    case "i" -> useItem();
                    default -> { continue; }
                }
                break;
            }
            catch (Exception e)
            {
                System.out.println("Input Error: " + e.getMessage());
            }
        }
    }

    private void useItem()
    {
        int input;
        while (true)
        {
            System.out.println("Which item do you want to use? (1, 2, 3, ...)");
            try
            {
                Scanner in = new Scanner(System.in);
                input = Integer.parseInt(in.nextLine().toLowerCase().trim());

                if (input > human.getInventory().getSize() ||
                        input < 1)
                {
                    continue;
                }
                System.out.println("You equipped a " + human.getInventory().itemToString(input -1));
                human.getInventory().removeItem(input - 1);
                break;
            }
            catch (Exception e)
            {
                System.out.println("Invalid input.");
            }
        }
    }

    private void goblinMove()
    {
        int x = human.getLocation().getX() - goblin.getLocation().getX();
        int y = human.getLocation().getY() - goblin.getLocation().getY();
        if (x > 0)
        {
            goblin.getLocation().setX(goblin.getLocation().getX() + 1);
        }
        else if (y > 0)
        {
            goblin.getLocation().setY(goblin.getLocation().getY() + 1);
        }
        else if (x < 0)
        {
            goblin.getLocation().setX(goblin.getLocation().getX() - 1);
        }

        else if (y < 0)
        {
            goblin.getLocation().setY(goblin.getLocation().getY() - 1);
        }
    }

    private boolean touching(Entity entity)
    {
        return human.getLocation().getX() == entity.getLocation().getX()
            && human.getLocation().getY() == entity.getLocation().getY();
    }

    private void combat(Entity attacker, Entity defender)
    {
        while (true)
        {
            try
            {
                System.out.println(attacker.attack(defender));
                TimeUnit.SECONDS.sleep(1);
                if (defender.getHealth() <= 0)
                {
                    break;
                }
                System.out.println(defender.attack(attacker));
                TimeUnit.SECONDS.sleep(1);
                if (attacker.getHealth() <= 0)
                {
                    break;
                }
            }
            catch (Exception e)
            { System.out.println(e.getMessage()); }
        }
    }

    @Override
    public String toString()
    {
        return "I'm the game object";
    }
}
