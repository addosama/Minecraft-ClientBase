package test.client.ui.clickgui.elements.setting.impl;

import net.minecraft.client.Minecraft;
import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.setting.SettingElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.impl.BooleanSetting;

import java.awt.*;

public class CheckBox extends SettingElement {
    private final BooleanSetting setting;

    public CheckBox(BooleanSetting setting, GuiElement parent) {
        super(parent);
        this.setting = setting;
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
    public float getHeight() {
        return 20;
    }
}
