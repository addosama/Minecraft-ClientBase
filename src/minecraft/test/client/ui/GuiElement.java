package test.client.ui;

import test.client.utils.RenderUtil;

public interface GuiElement {
    void draw(float mouseX, float mouseY, float partialTicks);
    default void mouseClicked(float mouseX, float mouseY, int mouseButton) {}
    default void mouseScrolled(float mouseX, float mouseY, float wheelInput) {}
    default void mouseReleased(int mouseX, int mouseY, int state) {}

    float getX();
    float getY();
    float getWidth();
    float getHeight();

    default boolean isHovering(float mouseX, float mouseY) {
        return RenderUtil.isHoveringArea(mouseX, mouseY, getX(), getY(), getWidth(), getHeight());
    }
}
