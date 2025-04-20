package test.client.utils.setting.impl;

import test.client.utils.setting.Setting;

import java.awt.*;

public class ColorSetting extends Setting<Color> {
    private final boolean allowAlpha;
    public ColorSetting(String name, String description, Color defaultValue, boolean allowAlpha) {
        super(name, description, defaultValue);
        this.allowAlpha = allowAlpha;
    }

    public ColorSetting(String name, String description, Color defaultValue) {
        this(name, description, defaultValue, true);
    }

    public boolean isAllowAlpha() {
        return allowAlpha;
    }
}
