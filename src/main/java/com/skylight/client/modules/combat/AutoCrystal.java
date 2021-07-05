package com.skylight.client.modules.combat;

import com.skylight.base.events.events.UpdateWalkingPlayerEvent;
import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.rotation.RotateManager;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@ModuleAnnotation(category = ModuleCategory.Combat)
public class AutoCrystal extends Module {
    public static final Setting<Boolean> place = new Setting<>("Place", true);
    public static final NumberSetting<Float> placeRange = new NumberSetting<>("PlaceRange", 0.0f, 5.0f, 6.0f, 1);
    public static final NumberSetting<Integer> placeDelay = new NumberSetting<>("PlaceDelay", 0, 1, 5, 1);
    public static final NumberSetting<Float> placeRangeWall = new NumberSetting<>("PlaceWallsRange", 0.0f, 3.0f, 6.0f, 1);
    public static final Setting<PlaceMode> placeMode = new Setting<>("PlaceMode", PlaceMode.Standard);

    public static final Setting<Boolean> destroy = new Setting<>("Break", true);
    public static final NumberSetting<Float> destroyRange = new NumberSetting<>("BreakRange", 0.0f, 5.0f, 6.0f, 1);
    public static final NumberSetting<Integer> destroyDelay = new NumberSetting<>("BreakDelay", 0, 1, 5, 1);
    public static final NumberSetting<Float> destroyRangeWall = new NumberSetting<>("BreakWallsRange", 0.0f, 3.0f, 6.0f, 1);

    public static final Setting<Boolean> predict = new Setting<>("Predict", true);
    public static final NumberSetting<Integer> predictTicks = new NumberSetting<>("PredictTicks", 0, 1, 5, 1);

    public static final NumberSetting<Integer> minDMG = new NumberSetting<>("MinPlaceDMG", 0, 5, 36, 1);
    public static final NumberSetting<Integer> maxSelfDMG = new NumberSetting<>("MaxSelfDMG", 0, 5, 36, 1);
    public static final Setting<Boolean> ignoreSelfDMG = new Setting<>("IgnoreSelfDMG", false);
    public static final Setting<Boolean> antiSuicide = new Setting<>("AntiSuicide", true);

    public static final Setting<Boolean> facePlace = new Setting<>("FacePlace", true);
    public static final NumberSetting<Integer> facePlaceHealth = new NumberSetting<>("FacePlaceHealth", 0, 10, 36, 1);

    public static final Setting<Boolean> armorBreak = new Setting<>("ArmorBreaker", true);
    public static final NumberSetting<Integer> armorBreakHealth = new NumberSetting<>("ArmorBreaker%", 0, 10, 100, 1);

    public static final Setting<RotateMode> rotate = new Setting<>("Rotate", RotateMode.None);
    public static final Setting<SwapMode> swap = new Setting<>("Rotate", SwapMode.Target);
    public static final Setting<Boolean> raytrace = new Setting<>("Raytrace", true);
    public static final Setting<EnumHand> swingHand = new Setting<>("SwingHand", EnumHand.MAIN_HAND);
    public static final Setting<Boolean> terrain = new Setting<>("IgnoreTerrain", true);

    public static final Setting<Boolean> debug = new Setting<>("Debug", true);

    private final List<EntityEnderCrystal> attemptedCrystals = new ArrayList<>();

    public EntityPlayer target = null;
    public BlockPos renderBlock = null;

    private double renderDamageVal = 0;

    private float yaw;
    private float pitch;

    private boolean alreadyAttacking;
    private boolean placeTimeoutFlag;
    private boolean hasPacketBroke;
    private boolean isRotating;
    private boolean didAnything;
    private boolean facePlacing;

    private long start = 0;
    private long crystalLatency;

    private int placeTimeout;
    private int breakTimeout;
    private int breakDelayCounter;
    private int placeDelayCounter;

    public BlockPos staticPos;
    public EntityEnderCrystal staticEndCrystal;

    public AutoCrystal() {
        registerSettings(
                place,
                placeRange,
                placeDelay,
                placeRangeWall,
                placeMode,

                destroy,
                destroyRange,
                destroyDelay,
                destroyRangeWall,

                predict,
                predictTicks,

                minDMG,
                maxSelfDMG,
                ignoreSelfDMG,
                antiSuicide,
                facePlace,
                facePlaceHealth,
                armorBreak,
                armorBreakHealth,

                rotate,
                swap,
                raytrace,
                swingHand,
                terrain,

                debug
        );
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public final void onUpdateWalkingPlayerEvent(UpdateWalkingPlayerEvent.Pre event) {
        if (rotate.getValue() == RotateMode.Full || isRotating) {
            RotateManager.setPlayerRotations(yaw, pitch);
            doAutoCrystal();
        }
    }

    public final void doAutoCrystal() {}




    public enum PlaceMode {
        Standard,
        Protocol
    }

    public enum RotateMode {
        None,
        Full
    }

    public enum SwapMode {
        None,
        Silent,
        Target
    }
}
