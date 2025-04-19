package test.client.module.impl.render;

import test.client.module.Module;
import test.client.module.ModuleCategory;
import test.client.utils.setting.impl.BooleanSetting;
import test.client.utils.setting.impl.DoubleSetting;
import test.client.utils.setting.impl.IntegerSetting;

public class TestModule extends Module {
    public BooleanSetting booleanSetting = new BooleanSetting("Bool", "", true);
    public DoubleSetting doubleSetting = new DoubleSetting("Double", "", 1.5, 0, 3, 0.01);

    public TestModule() {
        super("Test", "test module", ModuleCategory.Render);
        settings.add(booleanSetting);
        settings.add(doubleSetting);
        settings.add(new IntegerSetting("Int", "", 2, 0, 10, 1));
    }
}
