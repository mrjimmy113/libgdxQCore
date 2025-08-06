package com.quang.core.actionLib;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.quang.core.element.QLabel;

public class FontScaleToAction extends TemporalAction {
    private float startX, startY;
    private float endX, endY;
    private boolean isValid = false;

    protected void begin () {
        isValid = target instanceof QLabel;

        if(!isValid) return;

        QLabel lb = (QLabel) target;
        startX = lb.getFontScaleX();
        startY = lb.getFontScaleY();
    }

    protected void update (float percent) {
        if(!isValid) return;
        float x, y;
        if (percent == 0) {
            x = startX;
            y = startY;
        } else if (percent == 1) {
            x = endX;
            y = endY;
        } else {
            x = startX + (endX - startX) * percent;
            y = startY + (endY - startY) * percent;
        }
        QLabel lb = (QLabel) target;
        lb.setFontScale(x, y);
    }

    public void setScale (float x, float y) {
        endX = x;
        endY = y;
    }

    public void setScale (float scale) {
        endX = scale;
        endY = scale;
    }

    public float getX () {
        return endX;
    }

    public void setX (float x) {
        this.endX = x;
    }

    public float getY () {
        return endY;
    }

    public void setY (float y) {
        this.endY = y;
    }
}
