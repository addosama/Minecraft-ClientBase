package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.MathHelper;
import test.client.ui.GuiElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.impl.NumberSetting;

import java.awt.*;
import java.text.NumberFormat;

public class Slider implements SettingElement {
    private final NumberSetting<Number> setting;
    private final GuiElement parent;
    private final float y;

    public Slider(NumberSetting<Number> setting, GuiElement parent) {
        this.setting = setting;
        this.parent = parent;
        this.y = parent instanceof ModuleButton? 0 : parent.getY() + parent.getHeight();
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawCenteredString(setting.getName(), 4, 10, false, true, -1, false);
        float x = fr.getStringWidth(setting.getName()) + 8;
        float width = getWidth() - x - 4;
        double inv = (setting.getMaxValue().doubleValue() - setting.getMinValue().doubleValue());

        if (getModuleButton(this).isSliderDragging(this)) {
            double val = setting.getMinValue().doubleValue() + (MathHelper.clamp_double((mouseX - x) / width, 0, 1)) * inv;
            setting.setValue(val);
        }

        float w2 = (float) (width * setting.getValue().doubleValue() / inv);

        RenderUtil.drawRect(x, 4, width, 12, new Color(0,0,0,125).getRGB());
        RenderUtil.drawRect(x+1, 5, w2, 10, new Color(253, 137, 109).getRGB());
        fr.drawCenteredString(NumberFormat.getInstance().format(setting.getValue()), x + (width / 2), 10, true, true, -1, true);
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        float x = Minecraft.getMinecraft().fontRendererObj.getStringWidth(setting.getName()) + 8;
        if (RenderUtil.isHoveringArea(mouseX, mouseY, x, 4, getWidth() - x - 4, 12)) {
            if (mouseButton == 0) {
                getModuleButton(parent).setSliderDragging(this, true);
            }
        }
    }

    @Override
    public void mouseReleased(float mouseX, float mouseY, int state) {
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

    @Override
    public GuiElement getParent() {
        return parent;
    }

    public NumberSetting<Number> getSetting() {
        return setting;
    }
}
