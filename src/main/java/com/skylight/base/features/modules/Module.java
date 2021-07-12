package com.skylight.base.features.modules;

import com.skylight.base.events.events.RenderEvent2D;
import com.skylight.base.events.events.RenderEvent3D;
import com.skylight.base.settings.ParentSetting;
import com.skylight.base.settings.Setting;
import com.skylight.base.utils.chat.ChatUtils;
import com.skylight.base.utils.game.Game;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public abstract class Module implements Game {
    protected final String name = this.getClass().getSimpleName();
    protected ModuleCategory category = getAnnotation().category();
    protected int bind = getAnnotation().bind();

    protected final ArrayList<ParentSetting> parents = new ArrayList<>();

    protected final boolean enabledByDefault = getAnnotation().enabledByDefault();
    protected final boolean alwaysEnabled = getAnnotation().alwaysEnabled();

    protected boolean enabled = alwaysEnabled || enabledByDefault;
    protected boolean opened = false;
    protected boolean binding = false;
    protected boolean drawn = true;

    public boolean nullCheck() {
        return mc.player == null || mc.world == null;
    }

    public Module(){}

    private ModuleAnnotation getAnnotation() {
        if (getClass().isAnnotationPresent(ModuleAnnotation.class)) return getClass().getAnnotation(ModuleAnnotation.class);
        throw new IllegalStateException("ModuleAnnotation not found");
    }

    public void enable() {
        enabled = true;
        MinecraftForge.EVENT_BUS.register(this);
        ChatUtils.sendClientMessageSimple(getName() + ChatFormatting.GREEN + " ON");
        onEnable();
    }

    public void disable() {
        if (!alwaysEnabled) {
            enabled = false;
            MinecraftForge.EVENT_BUS.unregister(this);
            ChatUtils.sendClientMessageSimple(getName() + ChatFormatting.RED + " OFF");
            onDisable();
        }
    }

    public void toggle() {
        if (!alwaysEnabled) {
            setEnabled(!enabled);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (enabled) enable();
        else disable();
    }

    public String getName() {
        return name;
    }

    public ModuleCategory getCategory() {
        return category;
    }

    public int getBind() {
        return bind;
    }

    public void setBind(int bind) {
        this.bind = bind;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isBinding() {
        return binding;
    }

    public void setBinding(boolean binding) {
        this.binding = binding;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public void setDrawn(boolean drawn) {
        this.drawn = drawn;
    }

//    public ArrayList<Setting<?>> getSettings() {
//        return settings;
//    }

    /*public void registerSettings(Setting<?>... settings) {
        for (Setting<?> setting : settings) {
            this.settings.add(setting);
        }
    }*/

    public ArrayList<ParentSetting> getParents() {
        return parents;
    }

    public void registerParents(ParentSetting... parents) {
        for (ParentSetting parentSetting : parents) {
            this.parents.add(parentSetting);
        }
    }

    public void onEnable() {}

    public void onDisable() {}

    public void onUpdate() {}

    public void onTick() {}

    public void render2D(RenderEvent2D event) {}

    public void render3D(RenderEvent3D event) {}
}
