package test.client.utils.setting.impl;

import test.client.utils.setting.Setting;

public abstract class NumberSetting<T extends Number> extends Setting<T> {
    private final T minValue;
    private final T maxValue;
    private final T inc;

    public NumberSetting(String name, String description, T defaultValue, T minValue, T maxValue, T inc) {
        super(name, description, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.inc = inc;
    }

    public T getMinValue() {
        return minValue;
    }

    public T getMaxValue() {
        return maxValue;
    }

    public T getInc() {
        return inc;
    }
}
