package com.quang.core.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.quang.core.assets.QFontAsset;

public class QLabel extends Label {

    private Color shadowColor;
    private Vector2 shadowOffset = Vector2.Zero;
    private float shadowScale = 0;
    private Vector2 scaleOffset;
    private boolean isShadow = false;

    private Color outlineColor;
    private float outlineOffset;
    private boolean isOutline = false;

    private Color originColor;
    private float originX;
    private float originY;

    private static final float LOWEST_FONT_SCALE = 0.000001f;

    public QLabel(CharSequence text) {
        super(text, QFontAsset.DEFAULT_STYLE);
        setTouchable(Touchable.disabled);
    }

    public QLabel(CharSequence text, Skin skin) {
        super(text, skin);
        setTouchable(Touchable.disabled);
    }

    public QLabel(CharSequence text, Skin skin, String styleName) {
        super(text, skin, styleName);
        setTouchable(Touchable.disabled);
    }

    public QLabel(CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);
        setTouchable(Touchable.disabled);
    }

    public QLabel(CharSequence text, Skin skin, String fontName, String colorName) {
        super(text, skin, fontName, colorName);
        setTouchable(Touchable.disabled);
    }

    public QLabel(CharSequence text,QLabel template) {
        super(text,template.getStyle());
        setAlignment(template.getLabelAlign());
        setWidth(template.getWidth());
        setHeight(template.getHeight());
        setColor(template.getColor());
        setFontScale(template.getFontScaleX());
        setPosition(template.getX(),template.getY());
    }

    public QLabel(CharSequence text, LabelStyle style) {
        super(text, style);
        setTouchable(Touchable.disabled);
        setAlignment(Align.center);
    }

    public void setContainer(Actor btn) {
        setSize(btn.getWidth(),btn.getHeight());
        setPosition(btn.getX(),btn.getY());
    }

    public void setShadow(Color color, Vector2 scaleOff) {
        shadowColor = color;
        scaleOffset = scaleOff;
        if(originColor == null) {
            originColor = new Color();
            originColor.r = getColor().r;
            originColor.g = getColor().g;
            originColor.b = getColor().b;
            originColor.a = getColor().a;
        }

        isShadow = true;
        originX = getX();
        originY = getY();
    }

    public void changeShadowColor(Color color) {
        shadowColor = color;
    }

    public void setOutline(Color color, float offset) {
        outlineColor = color;
        outlineOffset = offset;
        if(originColor == null) {
            originColor = new Color();
            originColor.r = getColor().r;
            originColor.g = getColor().g;
            originColor.b = getColor().b;
            originColor.a = getColor().a;
        }
        isOutline = true;

    }

    public void changeOutlineColor(Color color) {
        outlineColor = color;
    }

    float originFontScale;

    @Override
    public void setFontScale(float fontScale) {
        if(fontScale <= LOWEST_FONT_SCALE) fontScale = LOWEST_FONT_SCALE;
        super.setFontScale(fontScale);
        originFontScale = fontScale;
    }

    @Override
    public void setFontScale(float fontScaleX, float fontScaleY) {
        if(fontScaleX <= LOWEST_FONT_SCALE) fontScaleX = LOWEST_FONT_SCALE;
        if(fontScaleY <= LOWEST_FONT_SCALE) fontScaleY = LOWEST_FONT_SCALE;
        super.setFontScale(fontScaleX,fontScaleY);
    }

    @Override
    public void setFontScaleX(float fontScaleX) {
        if(fontScaleX <= LOWEST_FONT_SCALE) fontScaleX = LOWEST_FONT_SCALE;
        super.setFontScaleX(fontScaleX);
    }

    @Override
    public void setFontScaleY(float fontScaleY) {
        if(fontScaleY <= LOWEST_FONT_SCALE) fontScaleY = LOWEST_FONT_SCALE;
        super.setFontScaleY(fontScaleY);
    }

    public void setOriginColor(Color color) {
        setColor(color);
        originColor = color;
    }

    @Override
    public void setScale(float scaleXY) {
        if(scaleXY == 0) scaleXY = 0.001f;
        super.setScale(scaleXY);
    }

    @Override
    public void setScaleX(float scaleX) {
        if(scaleX == 0) scaleX = 0.001f;
        super.setScaleX(scaleX);
    }

    @Override
    public void setScaleY(float scaleY) {
        if(scaleY == 0) scaleY = 0.001f;
        super.setScaleY(scaleY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //OUTLINE
/*        batch.getShader().setUniformf(DefaultShader.u_strength,0);
        batch.getShader().setUniformf(DefaultShader.u_color,getColor());*/
        originX = getX();
        originY = getY();

        if(isOutline) {
            setColor(outlineColor);

            setX(originX + outlineOffset);
            super.draw(batch, parentAlpha);
            setX(originX - outlineOffset);
            super.draw(batch, parentAlpha);
            setY(originY + outlineOffset);
            super.draw(batch, parentAlpha);
            setY(originY - outlineOffset);
            super.draw(batch, parentAlpha);
            setX(originX + outlineOffset);
            setY(originY + outlineOffset);
            super.draw(batch, parentAlpha);
            setX(originX + outlineOffset);
            setY(originY - outlineOffset);
            super.draw(batch, parentAlpha);
            setX(originX - outlineOffset);
            setY(originY - outlineOffset);
            super.draw(batch, parentAlpha);
            setX(originX - outlineOffset);
            setY(originY + outlineOffset);
            super.draw(batch, parentAlpha);
            setColor(originColor);
        }
        //SHADOW
        if(isShadow) {
            setColor(shadowColor);
            setX(originX + shadowOffset.x + scaleOffset.x);
            setY(originY + shadowOffset.y + scaleOffset.y);
            //super.setFontScale(originFontScale + shadowScale);
            super.draw(batch, parentAlpha);
            setColor(originColor);
            //super.setFontScale(originFontScale - shadowScale);

        }

        //BASIC

        setX(originX);
        setY(originY);
        super.draw(batch, parentAlpha);

    }

}
