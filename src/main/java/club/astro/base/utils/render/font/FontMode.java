package club.astro.base.utils.render.font;

import java.awt.*;

public enum FontMode {
    MINECRAFT(null),
    QUICKSAND(new CFontRenderer(new Font("Quicksand", Font.PLAIN, 18), true, true)),
    VERDANA(new CFontRenderer(new Font("Verdana", Font.PLAIN, 18), true, true));

    private final CFontRenderer renderer;

    FontMode(CFontRenderer renderer) {
        this.renderer = renderer;
    }

    public CFontRenderer getRenderer() {
        return renderer;
    }
}
