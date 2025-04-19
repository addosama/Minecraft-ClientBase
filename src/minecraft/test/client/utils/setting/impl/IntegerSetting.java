package test.client.utils.setting.impl;

public class IntegerSetting extends NumberSetting<Integer> {

    public IntegerSetting(String name, String description, Integer defaultValue, Integer minValue, Integer maxValue, Integer inc) {
        super(name, description, defaultValue, minValue, maxValue, inc);
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(Math.max(getMinValue(), Math.min(getMaxValue(), Math.round(value / getInc()) * getInc())));
    }
}
