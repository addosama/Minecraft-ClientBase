package test.client.module.impl.render;

import org.lwjgl.input.Keyboard;
import test.client.Client;
import test.client.module.Module;
import test.client.module.ModuleCategory;
import test.client.utils.setting.impl.ColorSetting;

import java.awt.*;
import java.util.Arrays;

public class CGUI extends Module {
    public static ColorSetting categoryColor1 = new ColorSetting("顶栏颜色(左)", "", new Color(253, 137, 109));
    public static ColorSetting categoryColor2 = new ColorSetting("顶栏颜色(右)", "", new Color(255, 218, 98));
    public static ColorSetting categoryTextColor = new ColorSetting("顶栏颜色(文本)", "", Color.WHITE);

    public static ColorSetting moduleTextColorD = new ColorSetting("模块名颜色(关闭)", "", Color.WHITE);
    public static ColorSetting moduleTextColorE = new ColorSetting("模块名颜色(开启)", "", categoryColor1.getDefaultValue());

    public static ColorSetting accentColor = new ColorSetting("主题颜色", "以后更新会细分", categoryColor1.getDefaultValue());
    public static ColorSetting bgColor = new ColorSetting("背景颜色", "", new Color(0, 0, 0, 150));
    public static ColorSetting textColor = new ColorSetting("主要文本颜色", "", Color.WHITE);

    public CGUI() {
        super("设置界面", "你看这条介绍时所在的页面", ModuleCategory.Render);
        settings.addAll(
                Arrays.asList(
                        categoryColor1, categoryColor2, categoryTextColor,
                        moduleTextColorD,moduleTextColorE,
                        accentColor, bgColor, textColor
                )
        );
        setKeybind(Keyboard.KEY_RSHIFT);
    }

    @Override
    protected void onEnable() {
        mc.displayGuiScreen(Client.INSTANCE.clickGui);
        setEnabled(false);
    }
}
