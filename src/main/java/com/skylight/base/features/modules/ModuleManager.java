package com.skylight.base.features.modules;

import com.skylight.base.utils.chat.ChatUtils;
import com.skylight.client.modules.client.Client;
import com.skylight.client.modules.client.Font;
import com.skylight.client.modules.client.GUI;
import com.skylight.client.modules.combat.AutoCrystal;
import com.skylight.client.modules.combat.AutoLog;
import com.skylight.client.modules.exploit.SpeedMine;
import com.skylight.client.modules.hud.BoxWatermark;
import com.skylight.client.modules.movement.*;
import com.skylight.client.modules.visual.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ModuleManager {
    protected static final ArrayList<Module> modules = new ArrayList<>();

    public void init() {
        modules.addAll(Arrays.asList(
                //combat
                new AutoCrystal(),
                new AutoLog(),
                //exploit
                new SpeedMine(),
                //move
                new Flight(),
                new Velocity(),
                new ReverseStep(),
                new Sprint(),
                new Speed(),
                //misc
                //visual
                new BetterChat(),
                new AspectRatio(),
                new Chams(),
                new BoxESP(),
                new BlockHighlight(),
                new VoidESP(),
                new HoleESP(),
                new BreakESP(),
                new ItemPhysics(),
                new ShulkerPeek(),
                //hud
                new BoxWatermark(),
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
