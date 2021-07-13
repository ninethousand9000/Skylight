package com.skylight.client.modules.movement;

import com.skylight.base.events.events.MoveEvent;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.mixins.accessors.IEntity;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.game.EntityUtils;
import com.skylight.base.utils.game.PlayerUtils;
import com.skylight.base.utils.misc.Timer;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.MobEffects;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModuleAnnotation(category = ModuleCategory.Movement)
public class Speed extends Module {
    public static final Setting<Mode> mode = new Setting<>("Mode", Mode.YPort);
    public static final NumberSetting<Float> yPortSpeed = new NumberSetting<>("YPortSpeed", 0.01f, 0.06f, 0.15f, 2);
    public static final NumberSetting<Float> jumpHeight = new NumberSetting<>("JumpHeight", 0.0f, 0.4f, 1.0f, 2);
    public static final NumberSetting<Float> timerSpeed = new NumberSetting<>("Timer", 1.0f, 1.15f, 1.5f, 2);

    public Speed() {
        registerParents(new ParentSetting("Settings", true, mode), new ParentSetting("SpeedSettings", false, yPortSpeed, jumpHeight, timerSpeed));
    }

    private boolean slowdown;
    private double playerSpeed;
    private final Timer timer = new Timer();

    @Override
    public void onEnable() {
        playerSpeed = PlayerUtils.getBaseMoveSpeed();
    }

    @Override
    public void onDisable() {
        EntityUtils.resetTimer();
        this.timer.reset();
    }

    @Override
    public void onUpdate() {
        if (nullCheck()) {
            this.disable();
            return;
        }

        if (mode.getValue() == Mode.YPort) {
            this.handleYPortSpeed();
        }

    }

    private void handleYPortSpeed() {
        if (!PlayerUtils.isMoving(mc.player) || mc.player.isInWater() && mc.player.isInLava() || mc.player.collidedHorizontally) {
            return;
        }

        if (mc.player.onGround) {
            EntityUtils.setTimer(1.15f);
            mc.player.jump();
            PlayerUtils.setSpeed(mc.player, PlayerUtils.getBaseMoveSpeed() + yPortSpeed.getValue());
        } else {
            mc.player.motionY = -1;
            EntityUtils.resetTimer();
        }
    }

    @SubscribeEvent
    public void onMove(MoveEvent event) {
        if (mc.player.isInLava() || mc.player.isInWater() || mc.player.isOnLadder() || ((IEntity)mc.player).getIsInWeb()) {
            return;
        }

        if (mode.getValue() == Mode.Strafe) {
            double speedY = jumpHeight.getValue();

            if (mc.player.onGround && PlayerUtils.isMoving(mc.player) && timer.passedMs(300)) {
                EntityUtils.setTimer(timerSpeed.getValue().floatValue());
                if (mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                    speedY += (mc.player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1f;
                }
                event.setY(mc.player.motionY = speedY);
                playerSpeed = PlayerUtils.getBaseMoveSpeed() * (EntityUtils.isColliding(0, -0.5, 0) instanceof BlockLiquid && !EntityUtils.isInLiquid() ? 0.9 : 1.901);
                slowdown = true;
                timer.reset();
            } else {
                EntityUtils.resetTimer();
                if (slowdown || mc.player.collidedHorizontally) {
                    playerSpeed -= (EntityUtils.isColliding(0, -0.8, 0) instanceof BlockLiquid && !EntityUtils.isInLiquid()) ? 0.4 : 0.7 * (playerSpeed = PlayerUtils.getBaseMoveSpeed());
                    slowdown = false;
                } else {
                    playerSpeed -= playerSpeed / 159.0;
                }
            }
            playerSpeed = Math.max(playerSpeed, PlayerUtils.getBaseMoveSpeed());
            double[] dir = PlayerUtils.forward(playerSpeed);
            event.setX(dir[0]);
            event.setZ(dir[1]);
        }
    }

    private enum Mode {
        Strafe,
        YPort
    }
}
