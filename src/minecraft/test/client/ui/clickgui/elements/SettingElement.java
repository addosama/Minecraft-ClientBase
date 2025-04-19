package test.client.ui.clickgui.elements;

import test.client.ui.GuiElement;

public interface SettingElement extends GuiElement {
    GuiElement getParent();
    default ModuleButton getModuleButton(GuiElement givenParent) {
        if (givenParent instanceof ModuleButton) return (ModuleButton) givenParent;
        else {
            return getModuleButton(((SettingElement) givenParent).getParent());
        }
    }
}
