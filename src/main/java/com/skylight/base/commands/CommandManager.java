package com.skylight.base.commands;

import com.skylight.base.commands.commands.PrefixCommand;
import com.skylight.base.commands.commands.ToggleCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {

    private static ArrayList<Command> commands = new ArrayList<>();

    /**
     * Scans all classes which extend {@link Command} using ClassGraph, and initializes them.
     * You can also do it manually, by manually adding every command to the commands ArrayList using commands.add(ExampleCommand.java);
     * This solution is simpler, especially if this is your first time working with java
     */
    public static void init() {
        commands.addAll(Arrays.asList(
                new ToggleCommand("toggles", "toggle", "tog", "t"),
                new PrefixCommand("set prefix", "prefix", "pref", "pre", "p")
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

