package com.skylight.base.events;

import com.skylight.Skylight;
import com.skylight.base.commands.Command;
import com.skylight.base.commands.CommandManager;
import com.skylight.base.events.events.PacketEvent;
import com.skylight.base.events.events.RenderEvent2D;
import com.skylight.base.events.events.RenderEvent3D;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleManager;
import com.skylight.base.utils.game.Game;
import com.skylight.base.utils.game.ServerUtils;
import com.skylight.base.utils.render.GLUProjection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
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
                for (Module module : Skylight.MODULE_MANAGER.getModules()) {
                    if (module.bindSetting.getValue().getKey() == Keyboard.getEventKey()) {
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
        for (Module module : Skylight.MODULE_MANAGER.getModules())
            if (module.isEnabled()) module.onUpdate();
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (nullCheck()) return;
        for (Module module : Skylight.MODULE_MANAGER.getModules())
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

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onRenderGameOverlayEvent(RenderGameOverlayEvent.Text event) {
        ScaledResolution resolution = new ScaledResolution(mc);
        RenderEvent2D renderEvent2D = new RenderEvent2D(event.getPartialTicks(), resolution);
        ModuleManager.getModules().stream().forEach(module -> {
            if (module.isEnabled()) module.onRender2d(renderEvent2D);
        });
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event) {
        if (event.isCanceled()) {
            return;
        }
        mc.profiler.startSection("skylight");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
        RenderEvent3D render3dEvent = new RenderEvent3D(event.getPartialTicks());
        GLUProjection projection = GLUProjection.getInstance();
        IntBuffer viewPort = GLAllocation.createDirectIntBuffer(16);
        FloatBuffer modelView = GLAllocation.createDirectFloatBuffer(16);
        FloatBuffer projectionPort = GLAllocation.createDirectFloatBuffer(16);
        GL11.glGetFloat(2982, modelView);
        GL11.glGetFloat(2983, projectionPort);
        GL11.glGetInteger(2978, viewPort);
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        projection.updateMatrices(viewPort, modelView, projectionPort, (double) scaledResolution.getScaledWidth() / (double) mc.displayWidth, (double) scaledResolution.getScaledHeight() / (double) mc.displayHeight);
        ModuleManager.getModules().stream().forEach(module -> {
            if (module.isEnabled()) module.onRender3d(render3dEvent);
        });
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        mc.profiler.endSection();
    }

    @SubscribeEvent
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            ServerUtils.update();
        }
    }
}
