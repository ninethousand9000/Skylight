package com.skylight.base.settings;

public class NumberSetting <T extends Number> extends Setting<T> {
    protected final T min;
    protected final T max;
    protected final int scale;

    public NumberSetting(String name, T min, T value, T max, int scale) {
        super(name, value);
        this.min = min;
        this.max = max;
        this.scale = scale;
    }

    public NumberSetting(Setting<T> parent, String name, T min, T value, T max, int scale) {
        super(parent, name, value);
        this.min = min;
        this.max = max;
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }
}
