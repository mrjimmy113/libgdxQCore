package com.quang.core.element;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.quang.core.assets.QImageAsset;
import com.quang.core.newTransform.NewImage;

public class QImage extends NewImage implements IQSortedActor {

    //region Attributes
    private boolean isNinePatch = false;
    private float fillYAmount = 1;
    private float fillXAmount = 1;
    private TextureRegion texBase;
    private int texWith;
    private int texHeight;
    private int texX;
    private int texY;
    private float alpha = 1f;
    private float scaleImgX = 1;
    private float scaleImgY = 1;
    protected String imgName = "";
    //endregion

    //region Constructor
    public QImage(NinePatch patch, TextureRegion texBase) {
        super(patch);
        patch.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setTouchable(Touchable.disabled);
        isNinePatch = true;
        this.texBase = texBase;
        texWith = texBase.getRegionWidth();
        texHeight = texBase.getRegionHeight();
        texX = texBase.getRegionX();
        texY = texBase.getRegionY();
    }

    public QImage(String texName) {
        super(QImageAsset.getImg(texName));
        imgName = texName;
        TextureRegion texture = QImageAsset.getImg(texName);
        texture.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        texBase = texture;
        texWith = texture.getRegionWidth();
        texHeight = texture.getRegionHeight();
        texX = texture.getRegionX();
        texY = texture.getRegionY();
        setTouchable(Touchable.disabled);
    }

    public QImage(TextureRegion texture) {
        super(texture);
        texture.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        texBase = texture;
        texWith = texture.getRegionWidth();
        texHeight = texture.getRegionHeight();
        texX = texture.getRegionX();
        texY = texture.getRegionY();
        setTouchable(Touchable.disabled);
    }
    //endregion

    //region Change Texture
    public void changeImage(String img) {
        if(imgName.equals(img)) return;
        imgName = img;
        changeImage(QImageAsset.getImg(img));
    }

    public void changeImage(TextureRegion texture) {
        texture.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegionDrawable up = new TextureRegionDrawable(texture);
        setWidth(texture.getRegionWidth() * scaleImgX);
        setHeight(texture.getRegionHeight() * scaleImgY);
        texBase = texture;
        texWith = texture.getRegionWidth();
        texHeight = texture.getRegionHeight();
        texX = texture.getRegionX();
        texY = texture.getRegionY();
        this.setDrawable(up);
    }

    public void changeImageFixSize(TextureRegion texture) {
        TextureRegionDrawable up = new TextureRegionDrawable(texture);
        texBase = texture;
        texWith = texture.getRegionWidth();
        texHeight = texture.getRegionHeight();
        texX = texture.getRegionX();
        texY = texture.getRegionY();
        this.setDrawable(up);
    }

    public void changeImageFixSize(String texture) {
        changeImageFixSize(QImageAsset.getImg(texture));
    }
    //endregion

    //region Setter
    public void setFillYAmount(float amount) {
        fillYAmount = MathUtils.clamp(amount,0,1);;
        int curY = (int)(texHeight * (1- fillYAmount)) + texY;

        texBase.setRegionY(curY);
        TextureRegionDrawable up = (TextureRegionDrawable) getDrawable();
        up.setRegion(texBase);
        setWidth(texBase.getRegionWidth() * scaleImgX);
        setHeight(texBase.getRegionHeight() * scaleImgY);
    }

    TextureRegion fillAmountRegion = new TextureRegion();

    public void setFillXAmount(float amount) {
        fillAmountRegion.setRegion(texBase);

        fillXAmount = MathUtils.clamp(amount,0,1);
        int curWidth = (int)(texWith * (fillXAmount));
        fillAmountRegion.setRegionWidth(curWidth);
        TextureRegionDrawable up = (TextureRegionDrawable) getDrawable();
        up.setRegion(fillAmountRegion);
        setWidth(texBase.getRegionWidth() * scaleImgX * fillXAmount);
        setHeight(texBase.getRegionHeight() * scaleImgY);
    }

    public void setImgScale(float scale) {
        scaleImgX = scale;
        scaleImgY = scale;
        setWidth(texBase.getRegionWidth() * scaleImgX);
        setHeight(texBase.getRegionHeight() * scaleImgY);
    }

    public void setScaleImgX(float scaleImgX) {
        this.scaleImgX = scaleImgX;
        setWidth(texBase.getRegionWidth() * scaleImgX);
    }

    public void setScaleImgY(float scaleImgY) {
        this.scaleImgY = scaleImgY;
        setHeight(texBase.getRegionHeight() * scaleImgY);
    }

    public void setSquareSize(float size) {
        setWidth(size);
        setHeight(size);
    }

    public String getImgName() {
        return imgName;
    }

    @Override
    public void setWidth(float width) {
        width = MathUtils.clamp(width,0,Float.MAX_VALUE);
        super.setWidth(width);
    }

    @Override
    public void setHeight(float height) {
        height = MathUtils.clamp(height,0,Float.MAX_VALUE);
        super.setHeight(height);
    }

    @Override
    public void setSize(float width, float height) {
        width = MathUtils.clamp(width,0,Float.MAX_VALUE);
        height = MathUtils.clamp(height,0,Float.MAX_VALUE);
        super.setSize(width, height);
    }
    //endregion

    //region Getter

    public TextureRegion getTexBase() {
        return texBase;
    }

    //endregion

    public void setColorAlpha(float alpha) {
        Color c = getColor();
        c.a = alpha;
        setColor(c);
    }


    @Override
    public String getAsset() {
        return getTexBase().getTexture().toString();
    }

    @Override
    public String toString() {
        return "QImage: " + getAsset() + " - " + imgName
                + " - Visible: " + isVisible();
    }

    @Override
    public Actor getActor() {
        return this;
    }
}
