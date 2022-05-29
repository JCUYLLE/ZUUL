import java.util.ArrayList;
/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class creates the class player. With getters and setters where needed
 *
 * @author Jolynn Cuylle
 * @version 2022.05.29
 */
public class Player {
    private final String name;
    private Room currentRoom;
    private final ArrayList<Item> items;
    private int maxWeight;
    /**
     * Constructor for our obj Player
     */
    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        items = new ArrayList<>();
        maxWeight= 10;
    }

    /**
     * Getter for the name of the Player
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }
    /**
     * Setter for the current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    /**
     * Check if a certain item is in the backpack
     */
    private boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }
    /**
     * Take function for picking up an item
     * checks if the max weight isn't exceeded
     */
    public boolean take(String itemName) {
        if (currentRoom.hasItem(itemName)) {
            Item item = currentRoom.getItem(itemName);
            if (getCurrentWeight()+ item.getWeight()<=maxWeight){
                items.add(item);
                return true;
            }
            else System.out.println("Your backpack is full");
            currentRoom.addItem(item);
        }
        return false;
    }
    /**
     * Getter for current zeight of the backpack
     */
    public int getCurrentWeight(){
        int currentWeight=0;
        for (Item i: items){
            currentWeight+=i.getWeight();
        }
        return currentWeight;
    }
    /**
     * Setter for max weight of the backpack
     */
    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
    /**
     * drop function to drop a certain item, if we have it  in our backpack
     */
    public boolean drop(String itemName) {
        if (this.hasItem(itemName)) {
            for (Item item : items) {
                if (item.getName().equals(itemName)) {
                    if (items.remove(item)) {
                        currentRoom.addItem(item);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * Check if something is in our backpack
     */
    public boolean inventoryCheck (String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lists up all the items that the player has in its backpack
     */
    public String getLongDescription() {
        String desc = "Player " + name + " has at the moment ";
        if (items.isEmpty()) {
            desc += "no items in the bag";
        } else {
            desc += "following items in the bag: ";
            for (Item item : items) {
                desc += "\n   " + item.getLongDescription();
            }
        }
        return desc;
    }
}
