package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import test.client.ui.GuiElement;

public class Text implements SettingElement {
    private final String text;
    private final GuiElement parent;
    private final float y;

    public Text(String text, GuiElement parent) {
        this.text = text;
        this.parent = parent;
        this.y = parent instanceof ModuleButton? 0 : parent.getY() + parent.getHeight();
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(text, 4, 6, false, true, -1, false);
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
        return 12;
    }

    @Override
    public GuiElement getParent() {
        return parent;
    }
}
