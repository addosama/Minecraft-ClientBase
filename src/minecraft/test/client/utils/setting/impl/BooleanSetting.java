package test.client.utils.setting.impl;

import test.client.utils.setting.Setting;

public class BooleanSetting extends Setting<Boolean> {
    public BooleanSetting(String name, String description, Boolean defaultValue) {
        super(name, description, defaultValue);
    }
}
