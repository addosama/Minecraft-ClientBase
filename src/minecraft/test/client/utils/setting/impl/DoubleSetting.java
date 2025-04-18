package test.client.utils.setting.impl;

import test.client.utils.setting.Setting;

public class DoubleSetting extends Setting<Double> {
    private final double minValue;
    private final double maxValue;
    private final double inc;

    public DoubleSetting(String name, String description, double defaultValue, double minValue, double maxValue, double inc) {
        super(name, description, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.inc = inc;
    }

    @Override
    public void setValue(Double value) {
        super.setValue(Math.max(minValue, Math.min(maxValue, Math.round(value / inc) * inc)));
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getInc() {
        return inc;
    }
}
