package com.skylight.base.utils.misc;

public class Counter {
    public int current;
    public int min;
    public int max;

    public boolean flip = false;

    public Counter(int current, int min, int max) {
        this.current = current;
        this.max = max;
        this.min = min;
    }

    public void count(int step) {
        for (int i = 0; i < step; i++) {
            if (current == max-1) flip = true;
            if (current == 0) flip = false;

            if (flip) current--;
            else current++;
        }
    }
}
