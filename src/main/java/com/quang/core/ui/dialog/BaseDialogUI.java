package com.quang.core.ui.dialog;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.quang.core.assets.QImageAsset;
import com.quang.core.element.QImage;
import com.quang.core.ui.ViewConstant;

public class BaseDialogUI {

    QImage dialogBg;

    public BaseDialogUI(BaseDialog baseDialog) {
        dialogBg = new QImage(QImageAsset.getNinePatch(QImageAsset.DIALOG_DUMMY),QImageAsset.getImg(QImageAsset.DIALOG_DUMMY));
        dialogBg.setSize(ViewConstant.getWidth(),ViewConstant.getHeight());
        dialogBg.setTouchable(Touchable.enabled);
        baseDialog.getBgG().addActor(dialogBg);

    }

}
