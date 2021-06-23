package club.astro.base.events;

import club.astro.Astro;
import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleManager;
import club.astro.base.utils.game.Game;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class EventManager implements Game {
    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() != Keyboard.KEY_NONE) {
                for (Module module : Astro.MODULE_MANAGER.getModules()) {
                    if (module.getBind() == Keyboard.getEventKey()) {
                        module.toggle();
                    }
                }
                try {
                    MinecraftForge.EVENT_BUS.register(event);
                } catch (Exception ignored) {}
            }
        }
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (nullCheck()) return;
        for (Module module : Astro.MODULE_MANAGER.getModules())
            if (module.isEnabled()) module.onUpdate();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (nullCheck()) return;
        for (Module module : Astro.MODULE_MANAGER.getModules())
            if (module.isEnabled()) module.onTick();
    }
}
