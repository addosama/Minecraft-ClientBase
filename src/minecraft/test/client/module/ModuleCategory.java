package test.client.module;

public enum ModuleCategory {
    Combat("战斗"),
    Movement("移动"),
    Render("视觉"),
    ;
    final String cn;
    ModuleCategory(String cn) {
        this.cn = cn;
    }

    public String getName() {
        return cn;
    }
}
