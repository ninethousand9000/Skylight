package club.astro;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

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

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    public static void startUp() {

    }

    public static void log(String message) {
        LOGGER.info(message);
    }

}
