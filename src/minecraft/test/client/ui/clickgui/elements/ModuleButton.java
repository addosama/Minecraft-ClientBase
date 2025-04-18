package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import test.client.module.Module;
import test.client.ui.GuiElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.Setting;
import test.client.utils.setting.impl.BooleanSetting;

import java.awt.*;
import java.util.ArrayList;

public class ModuleButton implements GuiElement {
    private final Module module;
    private float y;
    private boolean showSettings;

    private ArrayList<GuiElement> settings;

    public ModuleButton(Module module) {
        this.module = module;
        updateSettings();
        showSettings = false;
    }

    private void updateSettings() {
        ArrayList<GuiElement> list = new ArrayList<>();
        if (module.getSettings().isEmpty()) {
            list.add(new Text("这个功能目前没有设置", this, 0));
        } else {
            float y = 0;
            for (Setting<?> setting : module.getSettings()) {
                if (setting instanceof BooleanSetting) {
                    CheckBox c = new CheckBox((BooleanSetting) setting, this, 0);
                    list.add(c);
                    y += c.getHeight();
                }
            }
        }
        this.settings = list;
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, y, 0);
        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(module.getName(), module.isEnabled()? 8:4, 10, false,true, module.isEnabled()? new Color(253, 137, 109).getRGB():-1, true);
        if (showSettings) {
            GL11.glPushMatrix();
            GL11.glTranslatef(0, 20, 0);
            RenderUtil.drawRect(0, 0, getWidth(), getHeight()-20, new Color(0,0,0,100).getRGB());
            float f = 0;
            for (GuiElement e : settings) {
                GL11.glPushMatrix();
                GL11.glTranslatef(0, f, 0);
                e.draw(mouseX, mouseY, partialTicks);
                GL11.glPopMatrix();
                f = f + e.getHeight();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        updateSettings();
    }

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        if (RenderUtil.isHoveringArea(mouseX, mouseY, 0, 0, getWidth(), 20)) {
            if (mouseButton == 0) module.toggle();
            else if (mouseButton == 1) {
                // 显示设置
                showSettings = !showSettings;
            }
        } else {
            for (GuiElement e : settings) if (e.isHovering(mouseX, mouseY-20)) e.mouseClicked(mouseX, mouseY - e.getY(), mouseButton);
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
        float f = 20;
        if (showSettings) {
            for (GuiElement e : settings) f += e.getHeight();
        }
        return f;
    }
}
