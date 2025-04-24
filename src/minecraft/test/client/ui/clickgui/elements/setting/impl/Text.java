package test.client.ui.clickgui.elements.setting.impl;

import net.minecraft.client.Minecraft;
import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.setting.SettingElement;

public class Text extends SettingElement {
    private final String text;

    public Text(String text, GuiElement parent) {
        super(parent);
        this.text = text;
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(text, 4, 6, false, true, -1, false);
    }

    @Override
    public float getHeight() {
        return 12;
    }
}
