package test.client.utils;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

import java.awt.*;

public class RenderUtil {
    public static void drawRect(float x, float y, float width, float height, int color) {
        float left = x;
        float right = x+width;
        float top = y;
        float bottom = y+height;

        if (left < right)
        {
            float i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            float j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double)left, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)top, 0.0D).endVertex();
        worldrenderer.pos((double)left, (double)top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    public static void drawGradientRect(float x, float y, float width, float height, int leftTopColor, int rightTopColor, int leftBottomColor, int rightBottomColor) {
        float right = x+width;
        float bottom = y+height;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos((double)right, (double)y, 0).color(rightTopColor).endVertex();
        worldrenderer.pos((double)x, (double)y, 0).color(leftTopColor).endVertex();
        worldrenderer.pos((double)x, (double)bottom, 0).color(leftBottomColor).endVertex();
        worldrenderer.pos((double)right, (double)bottom, 0).color(rightBottomColor).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }
    public static void drawHGradientRect(float x, float y, float width, float height, int left, int right) {
        drawGradientRect(x, y, width, height, left, right, left, right);
    }
    public static void drawVGradientRect(float x, float y, float width, float height, int top, int bottom) {
        drawGradientRect(x, y, width, height, top, top, bottom, bottom);
    }
    public static void drawVHueBar(float x, float y, float width, float height, float saturation, float brightness) {
        float sH = height/5;
        int i = 1;
        int cT;
        int cB = Color.RED.getRGB();
        while (i <= 5) {
            cT = cB;
            cB = Color.HSBtoRGB(i*0.2f, saturation, brightness);
            drawVGradientRect(x, y + ((i-1)*sH), width, sH, cT, cB);
            i++;
        }
    }
    public static boolean isHoveringArea(float mouseX, float mouseY, float areaX, float areaY, float areaWidth, float areaHeight) {
        return (mouseY >= areaY && mouseY <= areaY+areaHeight) && (mouseX >= areaX && mouseX <= areaX+areaWidth);
    }
}
