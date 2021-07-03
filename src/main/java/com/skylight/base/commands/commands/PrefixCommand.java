package com.skylight.base.commands.commands;

import com.skylight.base.commands.Command;

import java.util.ArrayList;

public class PrefixCommand extends Command {
    /**
     * @param description The description of the Command
     * @param labels      The name of the Command (explained above)
     */
    public PrefixCommand(String description, String... labels) {
        super(description, labels);
    }

    @Override
    public void runCommand(ArrayList<String> args) {
        try {
            Command.setPrefix(args.get(0));
        }
        catch (Exception ignored) {}
    }
}
