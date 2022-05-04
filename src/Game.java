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
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game {
    private Parser parser;
    private Player player;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room hoofdingang, parking, inkomhal, kantine, doktersKabinetten, toiletten, kleedKamer, spoedgevallen, apotheek, helipad,
                operatieKamer, labo, mortuarium;
        Item medicine, siringe;

        // create the rooms
        hoofdingang = new Room("main entrance of the hospital");
        parking = new Room("where the cars are parked");
        inkomhal = new Room("great hall");
        kantine = new Room("Where you can find food");
        doktersKabinetten = new Room("rooms where the dokters have their appointments");
        toiletten = new Room("The pipi-room");
        kleedKamer = new Room("where docters change costumes into scrubs");
        spoedgevallen = new Room("where urgent cases can be investigated ");
        apotheek = new Room("outside the main entrance of the university where all the meds are kept");
        helipad = new Room("Where the helicopters land");
        operatieKamer = new Room("where the operations take place");
        labo = new Room("Where samples of body-fluids are examinated");
        mortuarium = new Room("where you end up when all goes wrong");

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
        apotheek.addItem(medicine);
        helipad.setExit("down", spoedgevallen);
        operatieKamer.setExit("East",spoedgevallen);
        operatieKamer.setExit("South",labo);
        labo.setExit("North",operatieKamer);
        mortuarium.setExit("Up",spoedgevallen);





        this.player = new Player("Meredith", hoofdingang);
        this.player = new Player("Dereck", operatieKamer);
        this.player = new Player("Cristina", doktersKabinetten);

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
        System.out.println("Type '" + CommandWord.HELP.toString() + "' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    private void printLocationInfo() {
        System.out.println(player.getCurrentRoom().getLongDescription());
        System.out.println(player.getLongDescription());
        System.out.println();
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
                eat();
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

    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void eat() {
        System.out.println("Je hebt nu gegeten en bent niet meer hongerig\n");
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
        }
    }

    private void take(Command command) {
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
