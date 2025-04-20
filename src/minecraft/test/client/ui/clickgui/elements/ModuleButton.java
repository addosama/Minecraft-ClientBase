package test.client.ui.clickgui.elements;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import test.client.module.Module;
import test.client.ui.GuiElement;
import test.client.utils.RenderUtil;
import test.client.utils.setting.Setting;
import test.client.utils.setting.impl.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ModuleButton implements GuiElement {
    private final Module module;
    private float y;
    private boolean showSettings;

    private ArrayList<GuiElement> settings;

    private final HashMap<NumberSetting<?>, Boolean> sliderDraggingMap;
    public void setSliderDragging(Slider e, boolean dragging) {
        sliderDraggingMap.put(e.getSetting(), dragging );
    }
    public boolean isSliderDragging(Slider e) {
        boolean r = sliderDraggingMap.get(e.getSetting());
        return r;
    }

    private final HashMap<ModeSetting, Boolean> selectorShowModeMap;
    public void setShowMode(Selector e, Boolean showMode) {
        selectorShowModeMap.put(e.getSetting(), showMode);
    }
    public boolean shouldShowMode(Selector e) {
        return selectorShowModeMap.get(e.getSetting());
    }

    private final HashMap<ColorSetting, Boolean> colorSettingShowPanelMap;
    public void setShowPanel(ColorSelector e, Boolean showPanel) {
        colorSettingShowPanelMap.put(e.getSetting(), showPanel);
    }
    public boolean shouldShowPanel(ColorSelector e) {
        return colorSettingShowPanelMap.get(e.getSetting());
    }

    private final HashMap<ColorSetting, Boolean> colorSettingDraggingMap;
    public void setColorSettingDragging(ColorSelector e, Boolean dragging) {
        colorSettingDraggingMap.put(e.getSetting(), dragging);
    }
    public boolean isColorSelectorDragging(ColorSelector e) {
        return colorSettingDraggingMap.get(e.getSetting());
    }

    public ModuleButton(Module module) {
        this.module = module;
        sliderDraggingMap = new HashMap<>();
        selectorShowModeMap = new HashMap<>();
        colorSettingShowPanelMap = new HashMap<>();
        colorSettingDraggingMap = new HashMap<>();
        showSettings = false;
        updateSettings();
    }

    private void updateSettings() {
        ArrayList<GuiElement> list = new ArrayList<>();
        if (module.getSettings().isEmpty()) {
            list.add(new Text("这个功能目前没有设置", this));
        } else {
            GuiElement last = this;
            for (Setting<?> setting : module.getSettings()) {
                if (!setting.isAvailable()) continue;
                if (setting instanceof BooleanSetting) {
                    last = new CheckBox((BooleanSetting) setting, last);
                    list.add(last);
                } else if (setting instanceof NumberSetting) {
                    last = new Slider((NumberSetting) setting, last);
                    sliderDraggingMap.putIfAbsent(((Slider)last).getSetting(), false);
                    list.add(last);
                } else if (setting instanceof ModeSetting) {
                    last = new Selector((ModeSetting) setting, last);
                    selectorShowModeMap.putIfAbsent(((Selector) last).getSetting(), false);
                    list.add(last);
                } else if (setting instanceof ColorSetting) {
                    last = new ColorSelector((ColorSetting) setting, last);
                    colorSettingShowPanelMap.putIfAbsent(((ColorSelector)last).getSetting(), false);
                    list.add(last);
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
                e.draw(mouseX, mouseY - e.getY(), partialTicks);
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
            for (GuiElement e : settings) if (e.isHovering(mouseX, mouseY-20)) e.mouseClicked(mouseX, mouseY - 20 - e.getY(), mouseButton);
        }
    }

    @Override
    public void mouseReleased(float mouseX, float mouseY, int state) {
        for (GuiElement e : settings) {
            if (e.isHovering(mouseX, mouseY-20)) e.mouseReleased(mouseX, mouseY - 20 - e.getY(), state);
            if (e instanceof Slider) {
                setSliderDragging((Slider) e, false);
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
        float f = 20;
        if (showSettings) {
            for (GuiElement e : settings) f += e.getHeight();
        }
        return f;
    }

    public void resetDragging() {
        for (GuiElement e : settings) {
            if (e instanceof Slider) {
                setSliderDragging((Slider) e, false);
            }
        }
    }
}
