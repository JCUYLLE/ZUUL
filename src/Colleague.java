import java.util.Random;
public class Colleague {
    private final String name;
    private boolean spokenToo = false;
    private final Room currentRoom;
    private String message ="";

    //private int currentHealth = 0;

    public Colleague(String name, Room currentRoom, String message){
            this.name = name;
            this.currentRoom = currentRoom;
            this.message = message;

            }

        public String getMessage() { return message;
        }

        public boolean isSpokenToo(){
        return spokenToo;
        }

        public void setSpokenToo(boolean spokenToo){
        this.spokenToo=spokenToo;
        }

        public String getName() {
            return name;
        }


        public Room getCurrentRoom() {
            return currentRoom;
        }
    }

