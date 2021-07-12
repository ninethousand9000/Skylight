package com.skylight.base.settings;

import java.util.ArrayList;

public class ParentSetting {
    protected final ArrayList<Setting<?>> settings = new ArrayList<>();
    protected final String name;
    protected boolean opened = true;

    /***
     * if true the parent will not be drawn and the settings will be linked to the module
      */
    protected boolean nullParent;

    public ParentSetting(String name, boolean nullParent, Setting<?>... settings) {
        this.name = name;
        this.nullParent = nullParent;

        if (!nullParent) opened = false;

        for (Setting<?> setting : settings) {
            this.settings.add(setting);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Setting<?>> getSettings() {
        return settings;
    }

    public boolean isOpened() {
        return opened;
    }

    public void registerSettings(Setting<?>... settings) {
        for (Setting<?> setting : settings) {
            this.settings.add(setting);
        }
    }

    public boolean isNullParent() {
        return nullParent;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}
