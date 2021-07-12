package com.skylight.base.settings;

import java.util.ArrayList;

public class Setting<T> {
    protected final String name;
    protected T value;
    protected boolean opened;
    protected boolean focus;
    private float alpha = 1f;

    protected final ArrayList<Setting<?>> subSettings = new ArrayList<>();

    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public Setting(ParentSetting parent, String name, T value) {
        this.name = name;
        this.value = value;

        parent.settings.add(this);
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public ArrayList<Setting<?>> getSubSettings() {
        return subSettings;
    }

    public void addSubSetting(Setting<?> subSetting) {
        subSettings.add(subSetting);
    }

    public float getAlpha() {

        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
