package com.skylight.client.modules.hud;

import com.skylight.base.events.events.RenderEvent2D;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.game.ServerUtils;
import com.skylight.base.utils.misc.CurrentTime;
import com.skylight.base.utils.render.RenderUtils2D;
import com.skylight.base.utils.render.RenderUtils3D;
import com.skylight.base.utils.render.font.FontUtils;
import com.skylight.client.modules.client.Client;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

@ModuleAnnotation(category = ModuleCategory.HUD)
public class BoxWatermark extends Module {
    public static final Setting<Boolean> clientName = new Setting<>("ClientName", true);
    public static final Setting<Boolean> username = new Setting<>("Username", true);
    public static final Setting<Boolean> ping = new Setting<>("Ping", true);
    public static final Setting<Boolean> tps = new Setting<>("Tps", true);
    public static final Setting<Boolean> server = new Setting<>("Server", true);
    public static final Setting<Boolean> time = new Setting<>("Time", true);

    public static final Setting<Color> bg = new Setting<>("Background", new Color(0x454545));

    public BoxWatermark() {
        registerParents(
                new ParentSetting("Components", false, clientName, username, ping, tps, server, time)
        );
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onRenderGameOverlayEvent(RenderGameOverlayEvent.Text event) {
        String text = "";

        try {
            if (clientName.getValue()) text += Client.name.getValue();
        } catch (Exception e) {}

        try {
            if (username.getValue()) text += (" | " + mc.player.getName());
        } catch (Exception e) {}

        try {
            if (ping.getValue()) text += (" | " + ServerUtils.getPing() + "ms");
        } catch (Exception e) {}

        try {
            if (tps.getValue()) text += (" | " + ServerUtils.getTPS() + " TPS");
        } catch (Exception e) {}

        try {
            if (server.getValue()) text += (" | " + mc.getCurrentServerData().serverIP.toLowerCase());
        } catch (Exception e) {}

        try {
            if (time.getValue()) text += (" | " + CurrentTime.getCurrentTime());
        } catch (Exception e) {}

        RenderUtils2D.drawRect(3, 3, FontUtils.getStringWidth(text) + 8, FontUtils.getFontHeight() + 6, bg.getValue());

        FontUtils.drawString(text, 5, 3 + (FontUtils.getFontHeight() / 2) + 1, Color.white);
    }
}
