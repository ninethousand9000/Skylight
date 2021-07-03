package com.skylight;

import com.skylight.base.events.EventManager;
import com.skylight.base.features.modules.ModuleManager;
import com.skylight.base.ui.bettermenu.BetterMainMenuLogin;
import com.skylight.client.modules.visual.BetterChat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * skylight
 *
 * @author ninethousand
 * @since 04/06/21
 * @version 1.0
 **/

@Mod(name = Skylight.MOD_NAME, modid = Skylight.MOD_ID, version = Skylight.MOD_VERSION)
public class Skylight {
    // Mod Information
    public static final String MOD_NAME = "Skylight";
    public static final String MOD_ID = "skylight";
    public static final String MOD_VERSION = "beta-1.0";

    // Defaults
    public static final String DEFAULT_COMMAND_PREFIX = ";";
    public static final int DEFAULT_GUI_KEY = Keyboard.KEY_I;

    // Managers & Utils
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();
    public static final EventManager EVENT_MANAGER = new EventManager();
    public static final BetterMainMenuLogin BETTER_MAIN_MENU = new BetterMainMenuLogin();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        startUp();
        if (MODULE_MANAGER.getModule(BetterChat.class).isEnabled()) MinecraftForge.EVENT_BUS.register(new BetterChat());
        Display.setTitle(MOD_NAME);
    }

    public static void startUp() {
        log("=============================");
        log("     Welcome To Skylight™    ");
        log("=============================");


        EVENT_MANAGER.init();
        log("Events Initialised!");

        MODULE_MANAGER.init();
        log("Modules Initialised!");

        /*CommandManager.init();
        log("Commands Initialised!");*/

        BETTER_MAIN_MENU.initGui();
    }

    public static void log(String message) {
        LOGGER.info(message);
    }

}
