package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import test.client.Client;
import test.client.module.Module;
import test.client.module.ModuleCategory;
import test.client.module.impl.render.CGUI;
import test.client.ui.GuiElement;
import test.client.utils.RenderUtil;

import java.util.ArrayList;

public class CategoryPanel implements GuiElement {
    private float x;
    private float y;

    private final ModuleCategory category;
    private final ArrayList<ModuleButton> modules;

    private boolean stacked;

    public CategoryPanel(ModuleCategory category, float x, float y) {
        this.category = category;
        this.modules = new ArrayList<>();
        this.x = x;
        this.y = y;
        stacked = true;
        for (Module m : Client.INSTANCE.moduleManager.getModules(category)) modules.add(new ModuleButton(m));
    }

    @Override
    public void draw(float mouseX, float mouseY, float partialTicks) {
        GL11.glPushMatrix();
        if (dragging) {
            x = mouseX - dragX + getX();
            y = mouseY - dragY + getY();
        }
        GL11.glTranslatef(x, y, 0);
        RenderUtil.drawRect(0, 0, getWidth(), getHeight(), CGUI.bgColor.getValue().getRGB());
        RenderUtil.drawHGradientRect(0,0,getWidth(),20, CGUI.categoryColor1.getValue().getRGB(), CGUI.categoryColor2.getValue().getRGB());
        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(category.getName(), 4, 10, false, true, CGUI.categoryTextColor.getValue().getRGB(), true);
        Minecraft.getMinecraft().fontRendererObj.drawCenteredString(stacked?"+":"-", 121-Minecraft.getMinecraft().fontRendererObj.getStringWidth(stacked?"+":"-"), 10, false, true, CGUI.categoryTextColor.getValue().getRGB(), true);
        if (!stacked) {
            float f = 20;
            for (ModuleButton m : modules) {
                m.setY(f);
                m.draw(mouseX, mouseY - m.getY(), partialTicks);
                f = f + m.getHeight();
            }
        }
        GL11.glPopMatrix();
    }

    private boolean dragging;
    private float dragX, dragY;

    @Override
    public void mouseClicked(float mouseX, float mouseY, int mouseButton) {
        if (RenderUtil.isHoveringArea(mouseX, mouseY, 0, 0, getWidth(), 20)) {
            if (mouseButton == 1 || (RenderUtil.isHoveringArea(mouseX, mouseY, 117-Minecraft.getMinecraft().fontRendererObj.getStringWidth(stacked?"+":"-"), 0, 125, 20) && mouseButton == 0)) {
                // 折叠
                stacked = !stacked;
                return;
            }
            if (mouseButton == 0) {
                dragging = true;
                dragX = mouseX;
                dragY = mouseY;
            }
        }

        if (!stacked) {
            for (ModuleButton m : modules) {
                if (m.isHovering(mouseX, mouseY)) {
                    m.mouseClicked(mouseX, mouseY - m.getY(), mouseButton);
                }
            }
        }
    }

    @Override
    public void mouseReleased(float mouseX, float mouseY, int state) {
        if (RenderUtil.isHoveringArea(mouseX, mouseY, 0, 0, getWidth(), 20)) {
            dragging = false;
            dragX = 0;
            dragY = 0;
        } else if (!stacked) {
            for (ModuleButton m : modules) {
                if (m.isHovering(mouseX, mouseY)) m.mouseReleased(mouseX, mouseY -m.getY(), state);
            }
        }
    }

    @Override
    public float getX() {
        return x;
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
        float h = 20;
        if (stacked) return h;
        for (ModuleButton m : modules) h = h + m.getHeight();
        return h;
    }

    public ArrayList<ModuleButton> getModules() {
        return modules;
    }
}
