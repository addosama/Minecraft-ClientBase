package test.client.module;

import com.google.common.eventbus.Subscribe;
import test.client.Client;
import test.client.event.impl.EventKey;
import test.client.module.impl.movement.Sprint;
import test.client.module.impl.render.CGUI;
import test.client.module.impl.render.HUD;

import java.util.*;

public class ModuleManager {
    private final LinkedHashMap<Class<? extends Module>, Module> moduleMap;

    public ModuleManager() {
        this.moduleMap = new LinkedHashMap<>();
        Client.INSTANCE.eventBus.register(this);
    }

    public void loadMods() {
        register(new Sprint());
        register(new HUD());
        register(new CGUI());
    }

    private void register(Module module) {
        moduleMap.put(module.getClass(), module);
    }

    public Collection<Module> getModules() {
        return moduleMap.values();
    }

    public Collection<Module> getModules(ModuleCategory category) {
        Collection<Module> mc = new ArrayList<>(moduleMap.values());
        mc.removeIf(m -> m.getCategory() != category);
        return mc;
    }

    public Collection<Module> getModules(boolean enabled) {
        Collection<Module> mc = new ArrayList<>(moduleMap.values());
        mc.removeIf(m -> m.isEnabled() != enabled);
        return mc;
    }

    @Subscribe
    public void onKey(EventKey key) {
        for (Module m : moduleMap.values()) {
            if (m.getKeybind() == key.getKey()) m.toggle();
        }
    }
}
