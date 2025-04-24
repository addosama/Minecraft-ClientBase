package test.client.ui.clickgui.elements.setting;

import test.client.ui.GuiElement;
import test.client.ui.clickgui.elements.ModuleButton;

public interface ISettingElement extends GuiElement {
    GuiElement getParent();
    default ModuleButton getModuleButton(GuiElement givenParent) {
        if (givenParent instanceof ModuleButton) return (ModuleButton) givenParent;
        else {
            return getModuleButton(((ISettingElement) givenParent).getParent());
        }
    }
}
