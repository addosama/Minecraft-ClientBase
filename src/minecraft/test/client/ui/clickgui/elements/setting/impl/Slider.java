package test.client.ui.clickgui.elements.setting.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.MathHelper;
import test.client.module.impl.render.CGUI;
import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.setting.SettingElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.impl.NumberSetting;

import java.awt.*;
import java.text.NumberFormat;

public class Slider extends SettingElement {
    private final NumberSetting<Number> setting;

    public Slider(NumberSetting<Number> setting, GuiElement parent) {
        super(parent);
        this.setting = setting;
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawCenteredString(setting.getName(), 4, 10, false, true, CGUI.textColor.getValue().getRGB(), false);
        float x = fr.getStringWidth(setting.getName()) + 8;
        float width = getWidth() - x - 4;
        double inv = (setting.getMaxValue().doubleValue() - setting.getMinValue().doubleValue());

        if (getModuleButton().isSliderDragging(this)) {
            double val = setting.getMinValue().doubleValue() + (MathHelper.clamp_double((mouseX - x) / width, 0, 1)) * inv;
            setting.setValue(val);
        }

        float w2 = (float) (width * setting.getValue().doubleValue() / inv);

        RenderUtil.drawRect(x, 4, width, 12, CGUI.bgColor.getValue().getRGB());
        RenderUtil.drawRect(x+1, 5, w2, 10, CGUI.accentColor.getValue().getRGB());
        fr.drawCenteredString(NumberFormat.getInstance().format(setting.getValue()), x + (width / 2), 10, true, true, CGUI.textColor.getValue().getRGB(), false);
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        float x = Minecraft.getMinecraft().fontRendererObj.getStringWidth(setting.getName()) + 8;
        if (RenderUtil.isHoveringArea(mouseX, mouseY, x, 4, getWidth() - x - 4, 12)) {
            if (mouseButton == 0) {
                getModuleButton().setSliderDragging(this, true);
            }
        }
    }

    @Override
    public float getHeight() {
        return 20;
    }

    public NumberSetting<Number> getSetting() {
        return setting;
    }
}
