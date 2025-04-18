package test.client.module;

import net.minecraft.client.Minecraft;
import test.client.Client;
import test.client.utils.setting.Setting;

import java.util.ArrayList;

public class Module {
    private final String name;
    private final String description;
    private final ModuleCategory category;

    private int keybind;
    private boolean enabled;

    protected final ArrayList<Setting<?>> settings;

    protected final Minecraft mc = Minecraft.getMinecraft();

    public Module(String name, String description, ModuleCategory category) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.keybind = 0;
        this.enabled = false;
        this.settings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public ModuleCategory getCategory() {
        return category;
    }

    public int getKeybind() {
        return keybind;
    }
    public void setKeybind(int keybind) {
        this.keybind = keybind;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        if (enabled) enable();
        else disable();
    }
    public void toggle() {
        setEnabled(!enabled);
    }

    private void enable() {
        this.enabled = true;
        onEnable();
        Client.INSTANCE.eventBus.register(this);
    }

    private void disable() {
        Client.INSTANCE.eventBus.unregister(this);
        onDisable();
        this.enabled = false;
    }

    protected void onEnable() {}
    protected void onDisable() {}

    public ArrayList<Setting<?>> getSettings() {
        return settings;
    }
}
