package club.astro.base.features.modules;

public enum ModuleCategory {
    Combat,
    Exploit,
    Movement,
    Misc,
    Visual,
    HUD,
    Client;

    private boolean openInGui = true;

    public boolean isOpenInGui() {
        return openInGui;
    }

    public void setOpenInGui(final boolean openInGui) {
        this.openInGui = openInGui;
    }
}
