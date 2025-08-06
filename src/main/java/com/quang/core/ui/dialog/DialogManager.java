package com.quang.core.ui.dialog;

import com.badlogic.gdx.utils.Disposable;
import com.quang.core.utils.IQDo;


import java.util.ArrayList;
import java.util.HashMap;

public class DialogManager implements Disposable {
    private static DialogManager instance;

    public static DialogManager ins() {
        if(instance == null) instance = new DialogManager();
        return instance;
    }

    private final HashMap<DialogIndex, BaseDialog> dicDialog = new HashMap<>();
    public ArrayList<DialogIndex> showedDialog = new ArrayList<>();


    public void Setup(BaseDialog[] baseDialogs) {
        for (BaseDialog v: baseDialogs) {
            AddDialog(v);
            v.setVisible(false);
        }
    }

    public boolean isDialogShowed(DialogIndex dialogIndex) {
        return showedDialog.contains(dialogIndex);
    }

    public BaseDialog GetDialog(DialogIndex dialogIndex) {
        return dicDialog.get(dialogIndex);
    }

    private void AddDialog(BaseDialog baseDialog) {
        dicDialog.put(baseDialog.getIndex(),baseDialog);
    }

    public void showDialog(final DialogIndex index, final DialogParam param) {
        if(showedDialog.contains(index)) return;
        dicDialog.get(index).setup(param);
        dicDialog.get(index).setVisible(true);
        dicDialog.get(index).showDialog();
        showedDialog.add(index);
        //QAdsHelper.selectContent(ViewManager.ins().getCurView().name(), getCurDialog().name());

    }

    public void showDialog(final DialogIndex index) {
        showDialog(index,null);
    }

    public void hideDialog(final DialogIndex index, final IQDo onHideDialog) {
        if(!showedDialog.contains(index)) return;
        dicDialog.get(index).onHideDialog(new IQDo() {
            @Override
            public void invoke() {
                if(onHideDialog != null) onHideDialog.invoke();
                dicDialog.get(index).setVisible(false);
                showedDialog.remove(index);
            }
        });
    }

    public void hideDialog(final DialogIndex index) {
        hideDialog(index,null);
    }

    public DialogIndex getCurDialog() {
        if(showedDialog.size() == 0) return DialogIndex.None;
        return showedDialog.get(showedDialog.size() - 1);
    }

    public boolean isAnyDialogShow() {
        return !showedDialog.isEmpty();
    }

    @Override
    public void dispose() {
        instance = null;
    }
}
