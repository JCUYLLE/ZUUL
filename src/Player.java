import java.util.ArrayList;

public class Player {
    private String name;
    private Room currentRoom;
    private final ArrayList<Item> items;
    private int maxWeight;

    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        items = new ArrayList<>();
        maxWeight= 10;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    private boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

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
    public int getCurrentWeight(){
        int currentWeight=0;
        for (Item i: items){
            currentWeight+=i.getWeight();
        }
        return currentWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

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
    public boolean inventoryCheck (String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }


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
