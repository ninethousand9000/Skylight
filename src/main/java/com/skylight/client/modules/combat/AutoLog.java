package com.skylight.client.modules.combat;

import com.skylight.base.features.modules.Module;
import com.skylight.base.features.modules.ModuleAnnotation;
import com.skylight.base.features.modules.ModuleCategory;
import com.skylight.base.settings.NumberSetting;
import com.skylight.base.settings.ParentSetting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.TextComponentString;

@ModuleAnnotation(category = ModuleCategory.Combat)
public class AutoLog extends Module {
    public static final NumberSetting<Integer> totemCount = new NumberSetting<>("Totems", 0, 1, 10, 1);
    public static final NumberSetting<Integer> health = new NumberSetting<>("Health", 0, 10, 36, 1);

    public AutoLog() {
        registerParents(new ParentSetting("Settings", true, totemCount, health));
    }

    @Override
    public void onUpdate() {
        if (nullCheck()) return;

        int totems = mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        float hp = mc.player.getHealth() + mc.player.getAbsorptionAmount();

        if (totems <= totemCount.getValue() && hp <= health.getValue()) {
            mc.player.connection.sendPacket(new SPacketDisconnect(new TextComponentString("Auto-Logged out at " + hp + " health")));
            disable();
        }
    }
}
