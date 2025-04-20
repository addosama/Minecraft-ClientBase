package test.client.ui.clickgui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import test.client.module.ModuleCategory;
import test.client.ui.clickgui.elements.CategoryPanel;
import test.client.ui.clickgui.elements.ModuleButton;

import java.io.IOException;
import java.util.ArrayList;

// TODO: 我知道代码也许有点乱 但我有时间会优化的
public class ClickGui extends GuiScreen {
    private final ArrayList<CategoryPanel> panels;
    private float y;
    public ClickGui() {
        panels = new ArrayList<>();
        float f = 10;
        for (ModuleCategory c : ModuleCategory.values()) {
            panels.add(new CategoryPanel(c, f, 5));
            f += 140;
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, y, 0);
        for (CategoryPanel p : panels) {
            p.draw(mouseX - p.getX(), mouseY-y - p.getY(), partialTicks);
        }
        GL11.glPopMatrix();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (CategoryPanel p : panels) {
            if (p.isHovering(mouseX, mouseY - y)) {
                p.mouseClicked(mouseX - p.getX(), mouseY - y - p.getY(), mouseButton);
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (CategoryPanel p : panels) {
            if (p.isHovering(mouseX, mouseY - y)) p.mouseReleased((mouseX - p.getX()), (mouseY - y - p.getY()), state);
            for (ModuleButton m : p.getModules()) m.resetDragging();
        }
    }

    protected void mouseScrolled(float mouseX, float mouseY, float wheelInput) {
        this.y += wheelInput;
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int wheelInput = Mouse.getDWheel()/sr.getScaleFactor();
        if (wheelInput != 0) {
            float x = (float) Mouse.getX() / sr.getScaleFactor();
            float y = (float) (sr.getScaledHeight() - Mouse.getY()) / sr.getScaleFactor();
            mouseScrolled(x, y, wheelInput);
        }
    }
}
