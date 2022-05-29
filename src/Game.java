import java.util.ArrayList;

/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.  Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * <p>
 * To play this game, create an instance of this class and call the "play"
 * method.
 * <p>
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game.  It also evaluates and
 * executes the commands that the parser returns.
 *
 *  @author Jolynn Cuylle
 *  * @version 2022.05.29
 */

public class Game {
    private final Parser parser;
    private Player player;
    private Room hoofdingang, parking, inkomhal, kantine, doktersKabinetten, toiletten, kleedKamer, spoedgevallen, apotheek, helipad,
            operatieKamer, labo, mortuarium;
    private Colleague Christina, McDreamy, Richard;
    private Patient p1,p2, p3, p4, p5, p6;
    private Item medicine, siringe;
    private ArrayList <Patient> patients;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        patients=new ArrayList<>();
        createRooms();
        parser = new Parser();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms() {


        // create the rooms
        hoofdingang = new Room("main entrance of the hospital");
        parking = new Room("Parking Lot");
        inkomhal = new Room("great entrance hall");
        kantine = new Room("Cantine, enjoy you're meal!");
        doktersKabinetten = new Room("room where the dokters have their appointments");
        toiletten = new Room("The pipi-room");
        kleedKamer = new Room("room where docters change costumes into scrubs");
        spoedgevallen = new Room("room where urgent cases can be investigated ");
        apotheek = new Room("Room where all the meds are kept");
        helipad = new Room("top-level heli-pad");
        operatieKamer = new Room("operation room");
        labo = new Room("room where samples of body-fluids are examinated");
        mortuarium = new Room("the room where you'll end up if Mer doesn't do her job");

        // create the items
        medicine = new Item("Medicine", "Medicine 5/5", 5);
        siringe = new Item("siringe", "Medicine 1/5", 1);


        // initialise room exits
        hoofdingang.setExit("South", inkomhal);
        hoofdingang.setExit("East", parking);
        parking.setExit("West", hoofdingang);
        inkomhal.setExit("North",hoofdingang);
        inkomhal.setExit("East",kantine);
        inkomhal.setExit("South",doktersKabinetten);
        inkomhal.setExit("West",spoedgevallen);
        kantine.setExit("West",inkomhal);
        kantine.setExit("South",toiletten);
        doktersKabinetten.setExit("North",inkomhal);
        doktersKabinetten.setExit("West",kleedKamer);
        toiletten.setExit("North",kantine);
        kleedKamer.setExit("North",spoedgevallen);
        kleedKamer.setExit("East",doktersKabinetten);
        spoedgevallen.setExit("North",apotheek);
        spoedgevallen.setExit("East",inkomhal);
        spoedgevallen.setExit("South",kleedKamer);
        spoedgevallen.setExit("West",operatieKamer);
        spoedgevallen.setExit("Up",helipad);
        spoedgevallen.setExit("Down",mortuarium);
        apotheek.setExit("South",spoedgevallen);
        helipad.setExit("down", spoedgevallen);
        operatieKamer.setExit("East",spoedgevallen);
        operatieKamer.setExit("South",labo);
        labo.setExit("North",operatieKamer);
        mortuarium.setExit("Up",spoedgevallen);
        apotheek.addItem(medicine);
        apotheek.addItem(siringe);
        operatieKamer.addItem(medicine);
        inkomhal.addItem(medicine);
        inkomhal.addItem(siringe);
        operatieKamer.addItem(siringe);
        spoedgevallen.addItem(siringe);
        spoedgevallen.addItem(medicine);

        //Making the colleagues
        Christina = new Colleague("Christina",operatieKamer, "God Lord Mer, just ask me to help, you know I'm am always here for you!");
        McDreamy = new Colleague("Dereck", doktersKabinetten,"Off course I want to help you love.");
        Richard = new Colleague("Richard", inkomhal, "I thought you'd never ask Mer, let's go!");

        //Creating the patients

        p1 = new Patient("Jane Doe", operatieKamer, 4);
        p2 = new Patient("Denny Duquette", spoedgevallen, 1);
        p3 = new Patient("Henry Burton",parking,2 );
        p4 = new Patient("Ellis Grey", labo,4);
        p5 = new Patient("Adele", helipad, 2);
        p6 = new Patient("Zola", doktersKabinetten, 3);


        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
        patients.add(p4);
        patients.add(p5);
        patients.add(p6);



        this.player = new Player("Meredith", hoofdingang);


    }

    /**
     * Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        printLocationInfo();
    }
    /**
     //Print out the location info for the player.
     */
    private void printLocationInfo() {
        int counter = 0;
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getLongDescription());
        for (Patient z : patients) {
            if (z.isHealed()){
                counter++;


            System.out.println("From the 6 patient you have already healed:  " + counter);
            System.out.println();
        }}
    }
    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            case HELP:
                printHelp();
                break;
            case LOOK:
                look();
                break;
            case EAT:
                eat(command);
                break;
            case TAKE:
                take(command);
                break;
            case DROP:
                drop(command);
                break;
            case GO:
                goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case HOME:
                home();
                break;
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("Player " + player.getName() + " is lost and alone, and wanders");
        System.out.println("around at the Hospital.");
        System.out.println();
        System.out.println("Possible command words are:   " + parser.showCommands());
        System.out.println();
    }
    /**
     //Checks in what room we are and gives us the long description
     */
    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
    /**
     //we can eat a cooky to enlarge our backpack and eat somthing
     */
    private void eat(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Eat What?");
            return;
        }
        if (command.getSecondWord().equals("Cooky")) {
            player.setMaxWeight(15);

            System.out.println("You have eaten a cooky and are no longer hungry\n");
            System.out.println("Your backpack has enlarged! Go back for more items!");
        }
    }
    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);
            printLocationInfo();
            checkRoomColleague(nextRoom);
            checkRoomPatient(nextRoom);
        }
    }
    /**
    //Check if there is colleague in the room that has something to say
    */
    private void checkRoomColleague(Room room){
        if(room.equals(operatieKamer))
            talkToColleague(Christina);
        if(room.equals(doktersKabinetten))
            talkToColleague(McDreamy);
        if(room.equals(inkomhal))
            talkToColleague(Richard);
    }
    /**
     //Check if there is Patient in the room that we can cure
     */
    private void checkRoomPatient(Room zieke){
        if(zieke.equals(operatieKamer))
            healPatient(p1,siringe, medicine );
        if(zieke.equals(spoedgevallen))
            healPatient(p2,siringe, medicine);
        if(zieke.equals(parking))
            healPatient(p3,siringe, medicine);
        if(zieke.equals(labo))
            healPatient(p4,siringe, medicine);
        if(zieke.equals(helipad))
            healPatient(p5,siringe, medicine);
        if(zieke.equals(doktersKabinetten))
            healPatient(p6,siringe, medicine);
    }
    /**
     //trugger the colleague to talk to you
     */
    private void talkToColleague(Colleague person){
        if(!person.isSpokenToo()){
            System.out.println("Meredith is asking for your help, would you like to help her with all of the patients?");
        System.out.println(person.getName()+" : " + person.getMessage());
        person.setSpokenToo(true);
        }

    }
    /**
     //if there is a patient we heal them by entering the room, if we have the needed medicine
     */
    private void healPatient(Patient person, Item item1, Item item2){
        if (!person.isHealed()){
            System.out.println("Heal this person as soon as possible....look at its health " + person.getHealth() );
            if (!player.inventoryCheck(item1.getName())||!player.inventoryCheck(item2.getName())){
                if (!player.inventoryCheck(item1.getName())){
                    person.setHealth(person.getCurrentHealth()+1);
                }else person.setHealth(person.getCurrentHealth()+5);
            }
            System.out.println(person.getCurrentHealth());
        }
        if (person.isHealed())
            System.out.println("Your Patient is healed, up to the next one");
    }
    /**
     //Checks if we want to take something and if the new weight isn't more than the maxweight. If not you take it.
     */
    private void take(Command command){
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.take(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }
    /**
     //Transports you right back to the beginning location, the home position
     */
    private void home(){
    player.setCurrentRoom(hoofdingang);
     printLocationInfo();
    }

    /**
     //Check if we have something in our backpack and if yes we drop it in that room
     */
    private void drop(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know what to drop...
            System.out.println("Drop what?");
            return;
        }
        String itemName = command.getSecondWord();
        if (player.drop(itemName)) {
            printLocationInfo();
        } else {
            System.out.println("There is no item here with the name " + itemName);
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}

