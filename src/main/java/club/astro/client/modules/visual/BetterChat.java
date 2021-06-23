package club.astro.client.modules.visual;

import club.astro.base.features.modules.Module;
import club.astro.base.features.modules.ModuleAnnotation;
import club.astro.base.features.modules.ModuleCategory;
import club.astro.base.settings.NumberSetting;
import club.astro.base.settings.Setting;
import club.astro.base.ui.betterchat.GuiBetterChat;
import club.astro.base.ui.clickgui.ClickGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

@ModuleAnnotation(category = ModuleCategory.Visual, enabledByDefault = true)
public class BetterChat extends Module {
    public static GuiBetterChat chatGUI;

    public static final Setting<Boolean> smooth = new Setting<>("Smooth", true);
    public static final Setting<Boolean> clear = new Setting<>("Clear", false);
    public static final NumberSetting<Integer> xOffset = new NumberSetting<>("xOffset", 0, 0, 10);
    public static final NumberSetting<Integer> yOffset = new NumberSetting<>("yOffset", 0, 0, 10);

    public BetterChat() {
        registerSettings(smooth, clear, xOffset, yOffset);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        MinecraftForge.EVENT_BUS.unregister(this);
        chatGUI = new GuiBetterChat(Minecraft.getMinecraft());
        ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, Minecraft.getMinecraft().ingameGUI, chatGUI, "field_73840_e");
    }
}