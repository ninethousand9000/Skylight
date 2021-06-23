package club.astro.base.settings;

import java.awt.*;
import java.util.ArrayList;

public class Setting<T> {
    protected final String name;
    protected T value;
    protected boolean opened;
    protected boolean typing;

    protected final ArrayList<Setting<?>> subSettings = new ArrayList<>();

    public Setting(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public Setting(Setting<?> parent, String name, T value) {
        this.name = name;
        this.value = value;

        if (parent.getValue() instanceof String) {
            Setting<String> setting = (Setting<String>) parent;
            setting.addSubSetting(this);
        }

        if (parent.getValue() instanceof Boolean) {
            Setting<Boolean> setting = (Setting<Boolean>) parent;
            setting.addSubSetting(this);
        }

        if (parent.getValue() instanceof Color) {
            Setting<Color> setting = (Setting<Color>) parent;
            setting.addSubSetting(this);
        }

        if (parent.getValue() instanceof Enum) {
            Setting<Enum> setting = (Setting<Enum>) parent;
            setting.addSubSetting(this);
        }

        if (parent.getValue() instanceof Integer) {
            Setting<Integer> setting = (Setting<Integer>) parent;
            setting.addSubSetting(this);
        }

        if (parent.getValue() instanceof Double) {
            Setting<Double> setting = (Setting<Double>) parent;
            setting.addSubSetting(this);
        }

        if (parent.getValue() instanceof Float) {
            Setting<Float> setting = (Setting<Float>) parent;
            setting.addSubSetting(this);
        }
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

    public boolean isTyping() {
        return typing;
    }

    public void setTyping(boolean typing) {
        this.typing = typing;
    }

    public ArrayList<Setting<?>> getSubSettings() {
        return subSettings;
    }

    public void addSubSetting(Setting<?> subSetting) {
        subSettings.add(subSetting);
    }
}