package club.astro.base.settings;

public class NumberSetting <T extends Number> extends Setting<T> {
    protected final T min;
    protected final T max;

    public NumberSetting(String name, T min, T value, T max) {
        super(name, value);
        this.min = min;
        this.max = max;
    }

    public NumberSetting(Setting<T> parent, String name, T min, T value, T max) {
        super(parent, name, value);
        this.min = min;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }
}
