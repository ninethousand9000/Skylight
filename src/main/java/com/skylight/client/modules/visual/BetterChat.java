package com.skylight.client.modules.visual;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.ui.betterchat.GuiBetterChat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@ModuleAnnotation(category = ModuleCategory.Visual, enabledByDefault = true)
public class BetterChat extends Module {
    public static GuiBetterChat chatGUI;

    public static final Setting<Boolean> clear = new Setting<>("Clear", false);
    public static final NumberSetting<Integer> xOffset = new NumberSetting<>("xOffset", 0, 0, 10, 1);
    public static final NumberSetting<Integer> yOffset = new NumberSetting<>("yOffset", 0, 0, 10, 1);

    public BetterChat() {
        registerParents(new ParentSetting("Settings", true, clear), new ParentSetting("Offset", false, xOffset, yOffset));
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        MinecraftForge.EVENT_BUS.unregister(this);
        chatGUI = new GuiBetterChat(Minecraft.getMinecraft());
        ObfuscationReflectionHelper.setPrivateValue(GuiIngame.class, Minecraft.getMinecraft().ingameGUI, chatGUI, "field_73840_e");
    }
}