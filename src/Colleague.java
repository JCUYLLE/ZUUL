/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class creates obj from the class colleague.
 * Checks if we spoke to the and getters for names.
 *
 * @author Jolynn Cuylle
 * @version 2022.05.29
 */
public class Colleague {
    private final String name;
    private boolean spokenToo = false;
    private final Room currentRoom;
    private String message ="";

    /**
     * construct a colleague
     */

    public Colleague(String name, Room currentRoom, String message){
            this.name = name;
            this.currentRoom = currentRoom;
            this.message = message;

            }
    /**
     * calls the message
     */
        public String getMessage() { return message;
        }
    /**
     * check if we have spoken to a person or not
     */
        public boolean isSpokenToo(){
        return spokenToo;
        }
    /**
     * Sets flag to yes if we have spoken to the person
     */
        public void setSpokenToo(boolean spokenToo){
        this.spokenToo=spokenToo;
        }
    /**
     * Getter for the name of the person
     */
        public String getName() {
            return name;
        }
    /**
     * Getter for room name that we are currently in
     */

        public Room getCurrentRoom() {
            return currentRoom;
        }
    }

