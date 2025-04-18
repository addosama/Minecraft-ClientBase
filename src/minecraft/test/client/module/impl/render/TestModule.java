package test.client.module.impl.render;

import test.client.module.Module;
import test.client.module.ModuleCategory;
import test.client.utils.setting.impl.BooleanSetting;

public class TestModule extends Module {
    public BooleanSetting booleanSetting = new BooleanSetting("Bool", "", true);
    public TestModule() {
        super("Test", "test module", ModuleCategory.Render);
        settings.add(booleanSetting);
    }
}
