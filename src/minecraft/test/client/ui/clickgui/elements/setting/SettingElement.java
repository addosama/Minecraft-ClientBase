package test.client.ui.clickgui.elements.setting;

import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.ModuleButton;

public abstract class SettingElement implements ISettingElement {
    private final GuiElement parent;
    private final float y;

    protected SettingElement(GuiElement parent) {
        this.parent = parent;
        this.y = parent instanceof ModuleButton ? 0 : parent.getY() + parent.getHeight();
    }

    @Override
    public final GuiElement getParent() {
        return parent;
    }

    @Override
    public final float getX() {
        return 0;
    }

    @Override
    public final float getY() {
        return y;
    }

    @Override
    public final float getWidth() {
        return 125;
    }
}
