package test.client.utils.setting.impl;

public class DoubleSetting extends NumberSetting<Double> {

    public DoubleSetting(String name, String description, double defaultValue, double minValue, double maxValue, double inc) {
        super(name, description, defaultValue, minValue, maxValue, inc);
    }

    @Override
    public void setValue(Number value) {
        super.setValue(Math.max(getMinValue(), Math.min(getMaxValue(), Math.round(value.doubleValue() / getInc()) * getInc())));
    }

    @Override
    public Double getValue() {
        return super.getValue().doubleValue();
    }
}
