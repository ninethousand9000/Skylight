package club.astro.base.commands;

import club.astro.base.commands.commands.ToggleCommand;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {

    private static ArrayList<Command> commands = new ArrayList<>();

    /**
     * Scans all classes which extend {@link Command} using ClassGraph, and initializes them.
     * You can also do it manually, by manually adding every command to the commands ArrayList using commands.add(ExampleCommand.java);
     * This solution is simpler, especially if this is your first time working with java
     */
    public static void init() {
        commands.addAll(Arrays.asList(
                new ToggleCommand("t", "toggle")
        ));
    }

    /**
     * Returns a list of the initialized Commands
     * @return ArrayList<Command> List of commands
     */
    public static ArrayList<Command> getCommands() {
        return commands;
    }

}

