package test.client.utils.setting.impl;

public class FloatSetting extends NumberSetting<Float> {

    public FloatSetting(String name, String description, Float defaultValue, Float minValue, Float maxValue, Float inc) {
        super(name, description, defaultValue, minValue, maxValue, inc);
    }

    @Override
    public void setValue(Float value) {
        super.setValue(Math.max(getMinValue(), Math.min(getMaxValue(), Math.round(value / getInc()) * getInc())));
    }
}
