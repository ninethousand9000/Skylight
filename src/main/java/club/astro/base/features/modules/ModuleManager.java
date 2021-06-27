package club.astro.base.features.modules;

import club.astro.client.modules.client.GUI;
import club.astro.client.modules.combat.Test1;
import club.astro.client.modules.combat.Test2;
import club.astro.client.modules.combat.Test3;
import club.astro.client.modules.visual.BetterChat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ModuleManager {
    protected final ArrayList<Module> modules = new ArrayList<>();

    public void init() {
        modules.addAll(Arrays.asList(
                new Test1(),
                new Test2(),
                new GUI(),
                new BetterChat()
        ));

        for (ModuleCategory moduleCategory : ModuleCategory.values()) {
            for (int i = 0; i < 10; i++) {
                modules.add(new Test3(moduleCategory));
            }
        }

        modules.sort(ModuleManager::order);
    }

    private static int order(Module module1, Module module2) {
        return module1.getName().compareTo(module2.getName());
    }

    public ArrayList<Module> getModules() {
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
}
