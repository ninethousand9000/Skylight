package club.astro.base.events;

import club.astro.Astro;
import club.astro.base.commands.Command;
import club.astro.base.commands.CommandManager;
import club.astro.base.features.modules.Module;
import club.astro.base.utils.chat.ChatUtils;
import club.astro.base.utils.game.Game;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;

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

    @SubscribeEvent
    public void onChatMessage(ClientChatEvent event) {
        if(event.getMessage().startsWith(Command.getPrefix())) {

            // This strips the message of the Prefix and then splits it into a String Array at the spaces. Then we convert the String Array into an ArrayList. The first index of the ArrayList will now be the label of the Command
            ArrayList<String> arguments = new ArrayList<>(Arrays.asList(event.getMessage().substring(Command.getPrefix().length()).split(" ")));

            // Now we loop through all Commands to check if one of the commands labels equals to the first index of the String ArrayList which is the label that just got input by the user.

            for(Command command : CommandManager.getCommands()) {
                for (String label : command.getLabels())     {
                    if(label.equalsIgnoreCase(arguments.get(0))) {
                        arguments.remove(0);
                        command.runCommand(arguments);
                    }
                }
            }

            event.setMessage("");
        }
    }
}
