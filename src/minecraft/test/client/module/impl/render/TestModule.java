package test.client.module.impl.render;

import test.client.module.Module;
import test.client.module.ModuleCategory;
import test.client.utils.setting.impl.*;

import java.awt.*;

public class TestModule extends Module {
    public BooleanSetting booleanSetting = new BooleanSetting("Bool", "", true);
    public DoubleSetting doubleSetting = new DoubleSetting("Double", "", 1.5, 0, 3, 0.01);

    public TestModule() {
        super("Test", "test module", ModuleCategory.Render);
        settings.add(booleanSetting);
        settings.add(doubleSetting);
        settings.add(new IntegerSetting("Int", "", 2, 0, 10, 1));
        settings.add(new ModeSetting("Mode", "", 2, "Mode 1", "Mode 2", "Mode 3"));
        settings.add(new ColorSetting("Color1", "", Color.RED, true));
        settings.add(new ColorSetting("Color2", "", Color.BLUE, false));
    }
}
