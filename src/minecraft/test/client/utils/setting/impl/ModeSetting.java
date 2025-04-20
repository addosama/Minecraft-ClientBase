package test.client.utils.setting.impl;

import test.client.utils.setting.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public class ModeSetting extends Setting<Integer> {
    private final ArrayList<String> modes;
    public ModeSetting(String name, String description, Integer defaultValue, String... modes) {
        super(name, description, defaultValue - 1);
        this.modes = new ArrayList<>(Arrays.asList(modes));
    }

    public String getModeName(int mode) {
        return modes.get(mode);
    }

    public String getCurrentName() {
        return getModeName(getValue());
    }
}
