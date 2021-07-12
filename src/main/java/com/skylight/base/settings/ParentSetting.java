package com.skylight.base.settings;

import java.util.ArrayList;

public class ParentSetting {
    protected final ArrayList<Setting<?>> subSettings = new ArrayList<>();
    protected final String name;
    protected boolean opened;

    public ParentSetting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Setting<?>> getSubSettings() {
        return subSettings;
    }

    public boolean isOpened() {
        return opened;
    }
}
