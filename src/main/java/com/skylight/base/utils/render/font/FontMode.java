package com.skylight.base.utils.render.font;

import com.skylight.Skylight;

import java.awt.*;
import java.io.File;

public enum FontMode {
    Default(null),
    Corbel(new CFontRenderer(new Font("Corbel", Font.PLAIN, Skylight.getFontSize()), true, true)),
    ComicSans(new CFontRenderer(new Font("Comic Sans MS", Font.PLAIN, Skylight.getFontSize()), true, true)),
    Consolas(new CFontRenderer(new Font("Consolas", Font.PLAIN, Skylight.getFontSize()), true, true)),
    SergoeUI(new CFontRenderer(new Font("Segoe UI", Font.PLAIN, Skylight.getFontSize()), true, true)),
    Verdana(new CFontRenderer(new Font("Verdana", Font.PLAIN, Skylight.getFontSize()), true, true));

    private final CFontRenderer renderer;

    FontMode(CFontRenderer renderer) {
        this.renderer = renderer;
    }

    public CFontRenderer getRenderer() {
        return renderer;
    }
}
