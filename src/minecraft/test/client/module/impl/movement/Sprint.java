package test.client.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import org.lwjgl.input.Keyboard;
import test.client.event.impl.EventPreUpdate;
import test.client.module.Module;
import test.client.module.ModuleCategory;

public class Sprint extends Module {
    public Sprint() {
        super("自动疾跑" ,"字面意思", ModuleCategory.Movement);
        this.setKeybind(Keyboard.KEY_LCONTROL);
    }

    @Subscribe
    public void onUpdate(EventPreUpdate e) {
        mc.thePlayer.setSprinting(true);
    }
}
