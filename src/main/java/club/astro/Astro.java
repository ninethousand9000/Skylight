package club.astro;

import club.astro.base.events.EventManager;
import club.astro.base.features.modules.ModuleManager;
import club.astro.base.utils.render.font.FontRenderer;
import club.astro.client.modules.visual.BetterChat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * astro.club
 *
 * @author ninethousand
 * @since 04/06/21
 * @version 1.0
 **/

@Mod(name = Astro.MOD_NAME, modid = Astro.MOD_ID, version = Astro.MOD_VERSION)
public class Astro {
    // Mod Information
    public static final String MOD_NAME = "Astro";
    public static final String MOD_ID = "astro";
    public static final String MOD_VERSION = "1.0";

    // Defaults
    public static final String DEFAULT_COMMAND_PREFIX = ";";
    public static final int DEFAULT_GUI_KEY = Keyboard.KEY_I;

    // Managers & Utils
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final FontRenderer FONT_RENDERER = new FontRenderer();
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();
    public static final EventManager EVENT_MANAGER = new EventManager();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        startUp();
        if (MODULE_MANAGER.getModule(BetterChat.class).isEnabled()) MinecraftForge.EVENT_BUS.register(new BetterChat());
        Display.setTitle(MOD_NAME);
    }

    public static void startUp() {
        EVENT_MANAGER.init();
        log("Events Initialised!");

        MODULE_MANAGER.init();
        log("Modules Initialised!");
    }

    public static void log(String message) {
        LOGGER.info(message);
    }

}
