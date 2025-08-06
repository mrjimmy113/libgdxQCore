package com.quang.core.element;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.quang.core.utils.IQDo;

public class QButton extends QImage{

    private EventListener onClickListener;

    public QButton(String texName) {
        super(texName);
        setTouchable(Touchable.enabled);
    }

    public QButton(TextureRegion region) {
        super(region);
        setTouchable(Touchable.enabled);
    }

    public QButton(NinePatch ninePatch, TextureRegion texBase) {
        super(ninePatch,texBase);
        setTouchable(Touchable.enabled);
    }

    private IQDo iqDo;



    public void setOnClick(IQDo iDo) {
        this.iqDo = iDo;
        if(onClickListener == null) {
            onClickListener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(!isButtonEnable) return;
                    if(iqDo != null) iqDo.invoke();
                }

            };
        }

        addListener(onClickListener);
    }

    public void removeOnClick() {
        if(onClickListener != null) removeListener(onClickListener);
        onClickListener = null;
        iqDo = null;
    }

    boolean isButtonEnable = true;

    public void setButtonEnable(boolean isEnable) {
        isButtonEnable = isEnable;
    }
}
