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
