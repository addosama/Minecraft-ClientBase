package test.client.ui.clickgui.elements.setting;

import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.ModuleButton;

public interface ISettingElement extends GuiElement {
    GuiElement getParent();
    default ModuleButton getModuleButton() {
        if (getParent() instanceof ModuleButton) return (ModuleButton) getParent();
        else {
            return ((ISettingElement) getParent()).getModuleButton();
        }
    }
}
