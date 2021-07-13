package com.skylight.base.utils.game;

import com.skylight.base.features.modules.Module;
import com.skylight.base.utils.misc.Timer;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

public class ServerUtils implements Game {
    private static final float[] tpsCounts = new float[10];
    private static final DecimalFormat format = new DecimalFormat("##.00#");
    private static final Timer timer = new Timer();
    private static float TPS = 20.0f;
    private static long lastUpdate = -1L;
    private static String serverBrand = "";

    public static boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    public static void onPacketReceived() {
        timer.reset();
    }

    public static boolean isServerNotResponding() {
        return timer.passedMs(1500);
    }

    public static long serverRespondingTime() {
        return timer.getPassedTimeMs();
    }

    public static void update() {
        //if(!mc.world.isRemote){return;}
        float tps;
        long currentTime = System.currentTimeMillis();
        if (lastUpdate == -1L) {
            lastUpdate = currentTime;
            return;
        }
        long timeDiff = currentTime - lastUpdate;
        float tickTime = (float) timeDiff / 20.0f;
        if (tickTime == 0.0f) {
            tickTime = 50.0f;
        }
        if ((tps = 1000.0f / tickTime) > 20.0f) {
            tps = 20.0f;
        }
        System.arraycopy(tpsCounts, 0, tpsCounts, 1, tpsCounts.length - 1);
        tpsCounts[0] = tps;
        double total = 0.0;
        for (float f : tpsCounts) {
            total += f;
        }
        if ((total /= tpsCounts.length) > 20.0) {
            total = 20.0;
        }
        TPS = Float.parseFloat(format.format(total));
        lastUpdate = currentTime;
    }

    public static void reset() {
        Arrays.fill(tpsCounts, 20.0f);
        TPS = 20.0f;
    }

    public static float getTpsFactor() {
        return 20.0f / TPS;
    }

    public static float getTPS() {
        return TPS;
    }

    public static String getServerBrand() {
        return serverBrand;
    }

    public static void setServerBrand(String brand) {
        serverBrand = brand;
    }

    public static int getPing() {
        if (nullCheck()) {
            return 0;
        }
        try {
            return Objects.requireNonNull(mc.getConnection()).getPlayerInfo(mc.getConnection().getGameProfile().getId()).getResponseTime();
        } catch (Exception e) {
            return 0;
        }
    }
}
