package test.client.ui.clickgui.elements.setting.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;
import test.client.module.impl.render.CGUI;
import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.setting.SettingElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.impl.ModeSetting;

import java.awt.*;

public class Selector extends SettingElement {
    private final ModeSetting setting;

    private final box mBox;

    public Selector(ModeSetting setting, GuiElement parent) {
        super(parent);
        this.setting = setting;
        this.mBox = new box(this);
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawCenteredString(setting.getName(), 4, 10, false, true, CGUI.textColor.getValue().getRGB(), false);

        // draw box
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(getWidth() - 4 - mBox.getWidth(), 4, 0);
            mBox.draw(mouseX - mBox.getX(), mouseY - mBox.getY(), partialTicks);
            GL11.glPopMatrix();
        }
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        if (RenderUtil.isHoveringArea(mouseX, mouseY, getWidth() - 4 - mBox.getWidth(), 4, mBox.getWidth(), mBox.getHeight())) {
            mBox.mouseClicked(mouseX - (getWidth() - 4 - mBox.getWidth()), mouseY - 4, mouseButton);
        }
    }

    @Override
    public float getHeight() {
        return shouldShowMode()? mBox.getHeight() + 8 : 20;
    }

    public ModeSetting getSetting() {
        return setting;
    }

    protected boolean shouldShowMode() {
        return getModuleButton().shouldShowMode(this);
    }

    class box implements GuiElement {
        private final Selector parent;
        private final float modeH;
        private final float w1;
        public box(Selector parent) {
            this.parent = parent;
            float h1 = 0;
            for (String ignored : parent.getSetting().getModes()) h1 = h1 + 12;
            modeH = h1;
            w1 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(setting.getCurrentName()) + 8;
        }

        @Override
        public void draw(float mouseX, float mouseY, float partialTicks) {
            FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
            RenderUtil.drawRect(0, 0, w1, getHeight(), CGUI.bgColor.getValue().getRGB());
            RenderUtil.drawRect(w1, 0, 12, 12, CGUI.accentColor.getValue().getRGB());
            fr.drawCenteredString(setting.getCurrentName(), (w1/2), 6, true, true, CGUI.textColor.getValue().getRGB(), false);
            fr.drawCenteredString(shouldShowMode()? "-" : "+", w1 + 6, 6, true, true, CGUI.textColor.getValue().getRGB(), false);
            if (shouldShowMode()) {
                float y2 = 12;
                for (String s: setting.getModes()) {
                    fr.drawCenteredString(s, (w1/2), y2 + 6, true, true, setting.getCurrentName().equals(s)? CGUI.accentColor.getValue().getRGB() : CGUI.textColor.getValue().getRGB(), false);
                    y2 += 12;
                }
            }
        }

        @Override
        public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
            if (mouseY <= 12) getModuleButton().setShowMode(parent, !shouldShowMode());
            else {
                float my2 = mouseY - 12;
                int index = (int) my2 / 12;
                parent.getSetting().setValue(index);
            }
        }

        @Override
        public float getX() {
            return 0;
        }

        @Override
        public float getY() {
            return 4;
        }

        @Override
        public float getWidth() {
            return w1 + 12;
        }

        @Override
        public float getHeight() {
            return parent.shouldShowMode()? modeH + 12 : 12;
        }
    }
}
