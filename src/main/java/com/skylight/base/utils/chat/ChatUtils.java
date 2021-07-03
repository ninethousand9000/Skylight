package com.skylight.base.utils.chat;

import com.skylight.base.utils.game.Game;
import com.skylight.client.modules.client.Client;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;

public class ChatUtils implements Game {
    public static void sendClientMessageSimple(String message) {
        mc.player.sendMessage(new TextComponentString(ChatFormatting.AQUA + Client.name.getValue() + " > " + ChatFormatting.WHITE + message));
    }

    public static void sendClientMessageSimpleWarning(String message) {
        mc.player.sendMessage(new TextComponentString(ChatFormatting.AQUA + Client.name.getValue() + " > " + ChatFormatting.RED + ChatFormatting.BOLD + message));
    }

    public static void sendClientMessage(String message) {
        if (mc.player != null) {
            final ITextComponent itc = new TextComponentString(ChatFormatting.AQUA + Client.name.getValue() + " > " + ChatFormatting.WHITE + message).setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("ninehack owns all!"))));
            mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(itc, 5936);
        }
    }

    public static void sendChatMessage(String message) {
        mc.player.sendChatMessage(message);
    }
}
