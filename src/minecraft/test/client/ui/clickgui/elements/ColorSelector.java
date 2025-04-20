package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import test.client.ui.GuiElement;
import test.client.utils.setting.impl.ColorSetting;

public class ColorSelector implements SettingElement {
    private final ColorSetting setting;
    private final GuiElement parent;
    private final float y;

    public ColorSelector(ColorSetting setting, GuiElement parent) {
        this.setting = setting;
        this.parent = parent;
        this.y = parent instanceof ModuleButton? 0 : parent.getY() + parent.getHeight();
    }

    @Override
    public GuiElement getParent() {
        return parent;
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        fr.drawCenteredString(setting.getName(), 4, 10, false, true, -1, false);
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        SettingElement.super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(float mouseX, float mouseY, int state) {
        SettingElement.super.mouseReleased(mouseX, mouseY, state);
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

    public ColorSetting getSetting() {
        return setting;
    }

    public boolean shouldShowPanel() {
        return getModuleButton(parent).shouldShowPanel(this);
    }
}
