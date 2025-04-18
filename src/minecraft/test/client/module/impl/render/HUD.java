package test.client.module.impl.render;

import com.google.common.eventbus.Subscribe;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.input.Keyboard;
import test.client.Client;
import test.client.event.impl.EventRenderGameOverlay;
import test.client.module.Module;
import test.client.module.ModuleCategory;

import java.util.ArrayList;

public class HUD extends Module {
    public HUD() {
        super("客户端信息", "显示客户端水印, 启用的功能等", ModuleCategory.Render);
        setKeybind(Keyboard.KEY_H);
    }

    @Subscribe
    public void onRender(EventRenderGameOverlay e) {
        FontRenderer fr = mc.fontRendererObj;
        fr.drawString(Client.NAME, 2, 2, -1);
        {
            ArrayList<Module> enabledModuleList = new ArrayList<>(Client.INSTANCE.moduleManager.getModules(true));
            enabledModuleList.sort((o1, o2) -> (fr.getStringWidth(o2.getName()) - fr.getStringWidth(o1.getName())));
            int i = 0;
            for (Module m : enabledModuleList) {
                fr.drawString(m.getName(), 2, 14 + i, -1);
                i = i + fr.FONT_HEIGHT + 2;
            }
        }
    }
}
