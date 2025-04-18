package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import test.client.module.Module;
import test.client.ui.GuiElement;
import test.client.utils.RenderUtil;

import java.awt.*;

public class ModuleButton implements GuiElement {
    private final Module module;
    private float y;
    public ModuleButton(Module module) {
        this.module = module;
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, y, 0);
        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(module.getName(), module.isEnabled()? 8:4, 10, false,true, module.isEnabled()? new Color(253, 137, 109).getRGB():-1, true);
        GL11.glPopMatrix();
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        if (RenderUtil.isHoveringArea(mouseX, mouseY, 0, 0, getWidth(), 20)) {
            if (mouseButton == 0) module.toggle();
            else if (mouseButton == 1) {
                // 显示设置
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

    public void setY(float y) {
        this.y = y;
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
