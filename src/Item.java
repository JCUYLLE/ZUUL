/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class creates the class Item. With getters and setters where needed.
 * We create a weigthcheck for the backpack
 *
 * @author Jolynn Cuylle
 * @version 2022.05.29
 */

public class Item {
    private String name;
    private String description;
    private double weight;
    /**
     * Constructor for an Item
     */
    public Item(String name, String description, double weight) {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }
    /**
     * Getter for the name of the Item
     */
    public String getName() {
        return name;
    }
    /**
     * Setter for the name of the Item
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter for the description of the item
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter for the description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Getter for the weight of the item
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Setter for the weight of the item
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * Getter that combines name + description + weight of the item
     */
    public String getLongDescription() {
        return this.name + " (" + this.description + ") with weight of " + this.weight + "kg";
    }
}
