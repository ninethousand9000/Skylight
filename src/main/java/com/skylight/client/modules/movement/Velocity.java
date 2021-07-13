package com.skylight.client.modules.movement;

import com.skylight.base.events.EventType;
import com.skylight.base.events.events.PacketEvent;
import com.skylight.base.events.events.PushEvent;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.mixins.accessors.ISPacketEntityVelocity;
import com.skylight.base.mixins.accessors.ISPacketExplosion;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleAnnotation(category = ModuleCategory.Movement)
public class Velocity extends Module {
    public static final Setting<Boolean> knockBack = new Setting<>("Knockback", true);
    public static final Setting<Boolean> noPush = new Setting<>("NoPush", true);
    public static final NumberSetting<Integer> horizontal = new NumberSetting<>("Horizontal", 0, 0, 100, 1);
    public static final NumberSetting<Integer> vertical = new NumberSetting<>("Vertical", 0, 0, 100, 1);
    public static final Setting<Boolean> ice = new Setting<>("Ice", false);
    public static final Setting<Boolean> blocks = new Setting<>("Blocks", false);
    public static final Setting<Boolean> explosions = new Setting<>("Explosions", false);
    public static final Setting<Boolean> liquids = new Setting<>("Liquids", false);
    public static final Setting<Boolean> rods = new Setting<>("FishingRods", false);


    public Velocity() {
        registerParents(
                new ParentSetting("Settings", true, knockBack, noPush),
                new ParentSetting("Multipliers", false, horizontal, vertical),
                new ParentSetting("Events", false, ice, explosions, liquids, rods)
        );
    }

    @Override
    public void onUpdate() {
        if (ice.getValue()) {
            Blocks.ICE.slipperiness = 0.6f;
            Blocks.PACKED_ICE.slipperiness = 0.6f;
            Blocks.FROSTED_ICE.slipperiness = 0.6f;
        }
    }

    @Override
    public void onDisable() {
        Blocks.ICE.slipperiness = 0.98f;
        Blocks.PACKED_ICE.slipperiness = 0.98f;
        Blocks.FROSTED_ICE.slipperiness = 0.98f;
    }

    @SubscribeEvent
    public void onPacketReceived(PacketEvent.Receive event) {
        if (event.getType() == EventType.PRE && Velocity.mc.player != null) {
            Entity entity;
            SPacketEntityStatus packet;
            SPacketEntityVelocity velocity;
            if (this.knockBack.getValue().booleanValue() && event.getPacket() instanceof SPacketEntityVelocity && (velocity = event.getPacket()).getEntityID() == Velocity.mc.player.getEntityId()) {
                if (this.horizontal.getValue().floatValue() == 0.0f && this.vertical.getValue().floatValue() == 0.0f) {
                    event.setCanceled(true);
                    return;
                }
                ((ISPacketEntityVelocity) velocity).setMotionX((int) ((float) velocity.getMotionX() * horizontal.getValue().floatValue()));
                ((ISPacketEntityVelocity) velocity).setMotionX((int) ((float) velocity.getMotionY() * vertical.getValue().floatValue()));
                ((ISPacketEntityVelocity) velocity).setMotionX((int) ((float) velocity.getMotionZ() * horizontal.getValue().floatValue()));
            }
            if (event.getPacket() instanceof SPacketEntityStatus && rods.getValue().booleanValue() && (packet = event.getPacket()).getOpCode() == 31 && (entity = packet.getEntity(Velocity.mc.world)) instanceof EntityFishHook) {
                EntityFishHook fishHook = (EntityFishHook) entity;
                if (fishHook.caughtEntity == Velocity.mc.player) {
                    event.setCanceled(true);
                }
            }
            if (this.explosions.getValue().booleanValue() && event.getPacket() instanceof SPacketExplosion) {
                //velocity = (SPacketExplosion)event.getPacket();
                SPacketExplosion velocity_ = event.getPacket();
                ((ISPacketExplosion) velocity_).setMotionX((int) (velocity_.getMotionX() * horizontal.getValue().floatValue()));
                ((ISPacketExplosion) velocity_).setMotionY((int) (velocity_.getMotionY() * vertical.getValue().floatValue()));
                ((ISPacketExplosion) velocity_).setMotionZ((int) (velocity_.getMotionZ() * horizontal.getValue().floatValue()));
            }
        }
    }

    @SubscribeEvent
    public void onPush(PushEvent event) {
        if (event.getType() == EventType.Type1 && noPush.getValue().booleanValue() && event.entity.equals(Velocity.mc.player)) {
            if (this.horizontal.getValue().floatValue() == 0.0f && this.vertical.getValue().floatValue() == 0.0f) {
                event.setCanceled(true);
                return;
            }
            event.x = -event.x * (double) this.horizontal.getValue().floatValue();
            event.y = -event.y * (double) this.vertical.getValue().floatValue();
            event.z = -event.z * (double) this.horizontal.getValue().floatValue();
        } else if (event.getType() == EventType.Type2 && blocks.getValue().booleanValue()) {
            event.setCanceled(true);
        } else if (event.getType() == EventType.Type3 && liquids.getValue().booleanValue() && Velocity.mc.player != null && Velocity.mc.player.equals(event.entity)) {
            event.setCanceled(true);
        }
    }
}
