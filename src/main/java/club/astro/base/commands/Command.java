package club.astro.base.commands;


import java.util.ArrayList;

public abstract class Command {

    /**
     * The Label is the name of the Command. For example (label = "bind") Command: .bind
     */
    private String label;

    private String description;

    /**
     * The command prefix
     */
    private static String prefix = "@";

    /**
     *
     * @param label The name of the Command (explained above)
     * @param description The description of the Command
     */
    public Command(String label, String description) {
        this.label = label;
        this.description = description;
    }

    /**
     * Overridable Method runCommand. Classes that extend the Command class can override this for their code to be run once the Command gets typed in the chat.
     * This class gets passed the arguments the Command had been called as an ArrayList of Strings.
     */
    public void runCommand(ArrayList<String> args) {

    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static void setPrefix(String prefix) {
        Command.prefix = prefix;
    }
}
