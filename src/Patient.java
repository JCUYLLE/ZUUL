/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class creates obj from the class Patient
 * Checks the health and if max health has been reached or not.
 *
 * @author Jolynn Cuylle
 * @version 2022.05.29
 */


public class Patient {
    private final String name;
    private int health;
    private boolean healed = false;
    private final Room currentRoom;
    private int currentHealth;

    public Patient(String name, Room currentRoom, int health){
        this.name = name;
        this.currentRoom = currentRoom;
        this.healed = false;

    }

    public void sethealed (boolean healed){
        this.healed = healed;
    }
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isHealed() {
        if (currentHealth >= 5)
            healed = true;
        return healed;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    public int getCurrentHealth() {return currentHealth;}
    public void setCurrentHealth(int currentHealth) {this.currentHealth = currentHealth;}
}