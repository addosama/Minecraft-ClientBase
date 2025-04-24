package test.client.ui.clickgui.elements.setting.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;
import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.ModuleButton;
import test.client.ui.clickgui.elements.setting.ISettingElement;
import test.client.utils.ColorUtil;
import test.client.utils.RenderUtil;
import test.client.utils.setting.impl.ColorSetting;

import java.awt.*;

public class ColorSelector implements ISettingElement {
    private final ColorSetting setting;
    private final GuiElement parent;
    private final float y;

    private final float panelH;

    public ColorSelector(ColorSetting setting, GuiElement parent) {
        this.setting = setting;
        this.parent = parent;
        this.y = parent instanceof ModuleButton ? 0 : parent.getY() + parent.getHeight();
        this.panelH = setting.isAllowAlpha()? 100 : 88;
    }

    @Override
    public GuiElement getParent() {
        return parent;
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawCenteredString(setting.getName(), 4, 10, false, true, -1, false);
        if (shouldShowPanel()) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0,20,0);

            float[] hsb = ColorUtil.RGBtoHSB(setting.getValue());
            int alpha = setting.isAllowAlpha()? setting.getValue().getAlpha() : 255;

            {
                // ID:1
                if (getModuleButton(parent).isColorSelectorDragging(this, 1)) {
                    if (RenderUtil.isHoveringArea(mouseX, mouseY-40, 4, 4, 104, 80)) {
                        hsb[1] = (mouseX-4)/104;
                        hsb[2] = Math.abs(1-(mouseY-44)/80);
                    }
                }
            }

            {
                // ID:2
                if (getModuleButton(parent).isColorSelectorDragging(this, 2)) {
                    if (RenderUtil.isHoveringArea(mouseX, mouseY-40, 113, 4, 8, 80)) {
                        hsb[0] = (mouseY-44) / 80;
                    }
                }
            }

            if (setting.isAllowAlpha()) {
                // ID:3
                if (RenderUtil.isHoveringArea(mouseX, mouseY-40, 4, 88, 117, 8)) {
                    fr.drawStringWithShadow("R:"+getModuleButton(parent).isColorSelectorDragging(this, 3), mouseX, mouseY-40, -1);
                    if (getModuleButton(parent).isColorSelectorDragging(this, 3)) {
                        // 你好朋友,请帮助我修复它并发起pull request,谢谢。
                        alpha = (int) (((mouseX - 4)/117) * 255);
                    }
                }
            }

            Color value = new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
            setting.setValue(new Color(value.getRed(), value.getGreen(), value.getBlue(), alpha));

            {
                RenderUtil.drawHGradientRect(4, 4, 104, 80, -1, Color.HSBtoRGB(hsb[0], 1, 1));
                RenderUtil.drawVGradientRect(4, 4, 104, 80, new Color(0, 0, 0, 0).getRGB(), Color.BLACK.getRGB());

                RenderUtil.drawVHueBar(113, 4, 8, 80, 1, 1);

                if (setting.isAllowAlpha()) {
                    RenderUtil.drawHGradientRect(4, 88, 117, 8, new Color(0, 0, 0, 0).getRGB(), setting.getValue().getRGB());
                }
            }

            GL11.glPopMatrix();
        }
        RenderUtil.drawRect(getWidth()-16, 4, 12, 12, setting.getValue().getRGB());
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        if (RenderUtil.isHoveringArea(mouseX, mouseY, getWidth()-16, 4, 12, 12)) {
            getModuleButton(parent).setShowPanel(this, !shouldShowPanel());
        }
        if (shouldShowPanel()) {
            if (RenderUtil.isHoveringArea(mouseX, mouseY, 4, 4, 104, 80)) {
                getModuleButton(parent).setColorSelectorDragging(this, 1, true);
            } else if (RenderUtil.isHoveringArea(mouseX, mouseY, 113, 4, 8, 80)) {
                getModuleButton(parent).setColorSelectorDragging(this, 2, true);
            } else if (RenderUtil.isHoveringArea(mouseX, mouseY, 4, 88, 117, 8)) {
                getModuleButton(parent).setColorSelectorDragging(this, 3, true);
            }
        }
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
        return shouldShowPanel()? panelH + 20 : 20;
    }

    public ColorSetting getSetting() {
        return setting;
    }

    public boolean shouldShowPanel() {
        return getModuleButton(parent).shouldShowPanel(this);
    }
}
