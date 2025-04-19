package test.client.utils.setting.impl;

public class FloatSetting extends NumberSetting<Float> {

    public FloatSetting(String name, String description, Float defaultValue, Float minValue, Float maxValue, Float inc) {
        super(name, description, defaultValue, minValue, maxValue, inc);
    }

    @Override
    public void setValue(Number value) {
        super.setValue(Math.max(getMinValue(), Math.min(getMaxValue(), Math.round(value.floatValue() / getInc()) * getInc())));
    }

    @Override
    public Float getValue() {
        return super.getValue().floatValue();
    }
}
