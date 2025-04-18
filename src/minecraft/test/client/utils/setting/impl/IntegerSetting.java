package test.client.utils.setting.impl;

import test.client.utils.setting.Setting;

public class IntegerSetting extends Setting<Integer> {
    private final int minValue;
    private final int maxValue;
    private final int inc;

    public IntegerSetting(String name, String description, int defaultValue, int minValue, int maxValue, int inc) {
        super(name, description, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.inc = inc;
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(Math.max(minValue, Math.min(maxValue, Math.round((float) value / inc) * inc)));
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getInc() {
        return inc;
    }
}
