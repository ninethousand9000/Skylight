package club.astro.base.commands;


import java.util.ArrayList;

public abstract class Command {

    /**
     * The Label is the name of the Command. For example (label = "bind") Command: .bind
     */
    private ArrayList<String> labels;

    private String description;

    /**
     * The command prefix
     */
    private static String prefix = "@";

    /**
     *
     * @param labels The name of the Command (explained above)
     * @param description The description of the Command
     */
    public Command(String description, String... labels) {
        this.description = description;

        for (String label : labels) {
            this.labels.add(label);
        }
    }

    /**
     * Overridable Method runCommand. Classes that extend the Command class can override this for their code to be run once the Command gets typed in the chat.
     * This class gets passed the arguments the Command had been called as an ArrayList of Strings.
     */
    public void runCommand(ArrayList<String> args) {

    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
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
