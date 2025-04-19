package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import test.client.ui.GuiElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.impl.NumberSetting;

import java.awt.*;

public class Slider implements GuiElement {
    private final NumberSetting<? extends Number> setting;
    private final GuiElement parent;
    private final float y;

    public Slider(NumberSetting<? extends Number> setting, GuiElement parent) {
        this.setting = setting;
        this.parent = parent;
        this.y = parent==null? 0 : parent.getY() + parent.getHeight();
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawCenteredString(setting.getName(), 4, 10, false, true, -1, false);
        float x = fr.getStringWidth(setting.getName()) + 8;
        float width = getWidth() - x - 4;
        float w2 = (float) (width * (setting.getValue().doubleValue() / (setting.getMaxValue().doubleValue() - setting.getMinValue().doubleValue())));
        RenderUtil.drawRect(x, 4, width, 12, new Color(0,0,0,125).getRGB());
        RenderUtil.drawRect(x+1, 5, w2, 10, new Color(253, 137, 109).getRGB());
        fr.drawCenteredString(setting.getValue().toString(), x + (width / 2), 10, true, true, -1, true);
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        GuiElement.super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(float mouseX, float mouseY, int state) {
        GuiElement.super.mouseReleased(mouseX, mouseY, state);
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
