package test.client.utils.setting.impl;

import test.client.utils.setting.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public class ModeSetting extends Setting<Integer> {
    private final ArrayList<String> modes;
    private final int defaultValue;
    public ModeSetting(String name, String description, String defaultValue, String... modes) {
        super(name, description, -1);
        this.modes = new ArrayList<>(Arrays.asList(modes));
        this.defaultValue = this.modes.indexOf(defaultValue);
    }

    @Override
    public Integer getDefaultValue() {
        return defaultValue;
    }

    public String getModeName(int mode) {
        return modes.get(mode);
    }
}
