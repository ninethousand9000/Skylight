package com.skylight.base.utils.color;

import java.awt.*;

public enum PresetColors {
    SkylightBlue(new Color(0x12C2E9)),
    SkylightPink(new Color(0xCD05FA));

    public Color color;

    PresetColors(Color color) {
        this.color = color;
    }
}
