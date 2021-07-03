package com.skylight.base.utils.misc;

public class MouseUtils {
    public static final boolean mouseHovering(final int posX, final int posY, final int width, final int height, final int mouseX, final int mouseY) {
        return mouseX > posX && mouseX < width && mouseY > posY && mouseY < height;
    }
}
