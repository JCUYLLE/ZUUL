public enum CommandWord {
    GO("go"), LOOK("look"), TAKE("take"), DROP("drop"), EAT("eat"), QUIT("quit"),
    HELP("?"), UNKNOWN("?");

    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}
