package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import test.client.ui.GuiElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.impl.BooleanSetting;

import java.awt.*;

public class CheckBox implements GuiElement {
    private final BooleanSetting setting;
    private final GuiElement parent;
    private final float y;

    public CheckBox(BooleanSetting setting, GuiElement parent) {
        this.setting = setting;
        this.parent = parent;
        this.y = parent==null? 0 : parent.getY() + parent.getHeight();
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(setting.getName(), 4, 10, false, true, -1, false);
        RenderUtil.drawRect(getWidth()-16, 4, 12, 12, new Color(0,0,0,80).getRGB());
        if (setting.getValue()) RenderUtil.drawRect(getWidth()-15, 5, 10, 10, new Color(253, 137, 109).getRGB());
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        setting.setValue(!setting.getValue());
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return 125;
    }

    @Override
    public float getHeight() {
        return 20;
    }
}
