package club.astro.base.features.modules;

import club.astro.base.utils.chat.ChatUtils;
import club.astro.client.modules.client.Client;
import club.astro.client.modules.client.Font;
import club.astro.client.modules.client.GUI;
import club.astro.client.modules.visual.AspectRatio;
import club.astro.client.modules.visual.BetterChat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ModuleManager {
    protected static final ArrayList<Module> modules = new ArrayList<>();

    public void init() {
        modules.addAll(Arrays.asList(
                //combat
                //exploit
                //move
                //misc
                //visual
                new BetterChat(),
                new AspectRatio(),
                //hud
                //client
                new Client(),
                new GUI(),
                new Font()
        ));

        modules.sort(ModuleManager::order);
    }

    private static int order(Module module1, Module module2) {
        return module1.getName().compareTo(module2.getName());
    }

    public static ArrayList<Module> getModules() {
        return modules;
    }

    public ArrayList<Module> getModulesByCategory(ModuleCategory category) {
        ArrayList<Module> modules = new ArrayList<>();
        for (Module module : this.modules) {
            if (module.getCategory() == category) modules.add(module);
        }
        return modules;
    }

    public <T extends Module> Module getModule(Class<T> clazz) {
        for (Module module : modules) {
            if (module.getClass().isAssignableFrom(clazz)) return module;
        }

        throw new NoSuchElementException();
    }

    public void toggleModuleByName(String name) {
        for (Module module : modules) {
            if (module.getName() == name) {
                module.toggle();
                ChatUtils.sendClientMessageSimple("\"" + name + "\" is a valid module");
            }
        }
        ChatUtils.sendClientMessageSimple(name + " is not a valid module");
    }
}
