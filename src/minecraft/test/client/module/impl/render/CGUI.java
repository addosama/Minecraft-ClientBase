package test.client.module.impl.render;

import org.lwjgl.input.Keyboard;
import test.client.Client;
import test.client.module.Module;
import test.client.module.ModuleCategory;

public class CGUI extends Module {
    public CGUI() {
        super("设置界面", "你看这条介绍时所在的页面", ModuleCategory.Render);
        setKeybind(Keyboard.KEY_RSHIFT);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(Client.INSTANCE.clickGui);
        setEnabled(false);
    }
}
