package com.skylight.base.commands.commands;

import com.skylight.base.commands.Command;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleManager;
import com.skylight.base.utils.chat.ChatUtils;

import java.util.ArrayList;

public class ToggleCommand extends Command {
    /**
     * @param description The description of the Command
     * @param labels      The name of the Command (explained above)
     */
    public ToggleCommand(String description, String... labels) {
        super(description, labels);
    }

    @Override
    public void runCommand(ArrayList<String> args) {
        try {
            String name = args.get(0);

            for (Module module : ModuleManager.getModules()) {
                if (module.getName().equalsIgnoreCase(name)) {
                    module.toggle();
                    return;
                }
            }

            ChatUtils.sendClientMessageSimpleWarning("Module (" + name + ") Not Found");
        }
        catch (Exception ignored) {}
    }
}
