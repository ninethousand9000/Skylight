package club.astro.base.commands.commands;

import club.astro.Astro;
import club.astro.base.commands.Command;
import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleManager;
import club.astro.base.utils.chat.ChatUtils;

import java.util.ArrayList;

public class ToggleCommand extends Command {

    /**
     * @param label       The name of the Command (explained above)
     * @param description The description of the Command
     */
    public ToggleCommand(String label, String description) {
        super(label, description);
    }

    @Override
    public void runCommand(ArrayList<String> args) {
        try {
            String name = args.get(0);

            for (Module module : ModuleManager.getModules()) {
                if (module.getName().equals(name)) {
                    module.toggle();
                    return;
                }
            }

            ChatUtils.sendClientMessageSimpleWarning("Module (" + name + ") Not Found");
        }
        catch (Exception ignored) {}
    }
}
