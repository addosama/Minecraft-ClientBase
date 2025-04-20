package test.client.utils;

import java.awt.*;

public class ColorUtil {
    public static float[] RGBtoHSB(Color color) {
        float[] val = new float[3];
        return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), val);
    }
}
