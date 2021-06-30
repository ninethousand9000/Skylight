package club.astro.base.commands.commands;

import club.astro.base.commands.Command;
import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleManager;
import club.astro.base.utils.chat.ChatUtils;

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
