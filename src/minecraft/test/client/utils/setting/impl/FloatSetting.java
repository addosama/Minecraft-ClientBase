package test.client.utils.setting.impl;

import test.client.utils.setting.Setting;

public class FloatSetting extends Setting<Float> {
    private final float minValue;
    private final float maxValue;
    private final float inc;

    public FloatSetting(String name, String description, float defaultValue, float minValue, float maxValue, float inc) {
        super(name, description, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.inc = inc;
    }

    @Override
    public void setValue(Float value) {
        super.setValue(Math.max(minValue, Math.min(maxValue, Math.round(value / inc) * inc)));
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public float getInc() {
        return inc;
    }
}
