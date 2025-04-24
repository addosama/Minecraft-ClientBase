package test.client;

import com.google.common.eventbus.EventBus;
import org.lwjgl.opengl.Display;
import test.client.module.ModuleManager;
import test.client.ui.clickgui.ClickGui;

// TODO: 参数保存
public class Client {
    public static Client INSTANCE;

    public static final String NAME = "TestClient";

    public EventBus eventBus;
    public ModuleManager moduleManager;

    public ClickGui clickGui;

    public Client() {
        INSTANCE = this;
    }

    public void start() {
        this.eventBus = new EventBus();
        this.moduleManager = new ModuleManager();

        moduleManager.loadMods();

        clickGui = new ClickGui();

        Display.setTitle(NAME);
    }

    public void stop() {

    }
}
