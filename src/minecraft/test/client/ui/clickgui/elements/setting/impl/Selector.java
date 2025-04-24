package test.client.ui.clickgui.elements.setting.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;
import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.ModuleButton;
import test.client.ui.clickgui.elements.setting.ISettingElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.impl.ModeSetting;

import java.awt.*;

public class Selector implements ISettingElement {
    private final ModeSetting setting;
    private final GuiElement parent;
    private final float y;

    private final box mBox;

    public Selector(ModeSetting setting, GuiElement parent) {
        this.setting = setting;
        this.parent = parent;
        this.y = parent instanceof ModuleButton ? 0 : parent.getY() + parent.getHeight();
        this.mBox = new box(this);
    }

    @Override
    public GuiElement getParent() {
        return parent;
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawCenteredString(setting.getName(), 4, 10, false, true, -1, false);

        // draw box
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(getWidth() - 4 - mBox.getWidth(), 4, 0);
            mBox.draw(mouseX - mBox.getX(), mouseY - mBox.getY(), partialTicks);
            GL11.glPopMatrix();
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
        return getModuleButton(parent).shouldShowMode(this);
    }

    class box implements GuiElement {
        private final Selector parent;
        private final float modeH;
        private final float w1;
        public box(Selector parent) {
            this.parent = parent;
            float h1 = 0;
            for (String s : parent.getSetting().getModes()) h1 = h1 + 12;
            modeH = h1;
            w1 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(setting.getCurrentName()) + 8;
        }

        @Override
        public void draw(float mouseX, float mouseY, float partialTicks) {
            FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
            RenderUtil.drawRect(0, 0, w1, getHeight(), new Color(0,0,0, 125).getRGB());
            RenderUtil.drawRect(w1, 0, 12, 12, new Color(253, 137, 109).getRGB());
            fr.drawCenteredString(setting.getCurrentName(), (w1/2), 6, true, true, -1, false);
            fr.drawCenteredString(shouldShowMode()? "-" : "+", w1 + 6, 6, true, true, -1, false);
            if (shouldShowMode()) {
                float y2 = 12;
                for (String s: setting.getModes()) {
                    fr.drawCenteredString(s, (w1/2), y2 + 6, true, true, setting.getCurrentName().equals(s)? new Color(253, 137, 109).getRGB() : -1, false);
                    y2 += 12;
                }
            }
        }

        @Override
        public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
            if (mouseY <= 12) getModuleButton(parent).setShowMode(parent, !shouldShowMode());
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
