package com.paint;

import android.graphics.RectF;

public class ObiektDoNarysowania {

    public int kolor;
    public RectF figura;
    public int type;

    public ObiektDoNarysowania(int kolor, RectF figura, int type) {
        this.kolor = kolor;
        this.figura = figura;
        this.type = type;
    }
}