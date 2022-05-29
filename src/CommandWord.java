/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author Jolynn Cuylle
 * @version 2022.05.29
 */
public enum CommandWord {
    GO("Go"), LOOK("look"), TAKE("take"), DROP("drop"), EAT("eat"), QUIT("quit"),
    HELP("?"), UNKNOWN("?"), HOME ("home");

    private final String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }


}
