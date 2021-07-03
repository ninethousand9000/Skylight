package com.skylight.base.utils.sound;

import com.skylight.base.utils.game.Game;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;

public class SoundUtils implements Game {
    public static void playGuiClick() {
        mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
