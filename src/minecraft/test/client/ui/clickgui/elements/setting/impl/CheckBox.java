package test.client.ui.clickgui.elements.setting.impl;

import net.minecraft.client.Minecraft;
import test.client.module.impl.render.CGUI;
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
        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(setting.getName(), 4, 10, false, true, CGUI.textColor.getValue().getRGB(), false);
        RenderUtil.drawRect(getWidth()-16, 4, 12, 12, CGUI.bgColor.getValue().getRGB());
        if (setting.getValue()) RenderUtil.drawRect(getWidth()-15, 5, 10, 10, CGUI.accentColor.getValue().getRGB());
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
