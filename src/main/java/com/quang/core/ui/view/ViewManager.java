package com.quang.core.ui.view;

import com.badlogic.gdx.utils.Disposable;
import com.quang.core.utils.IQDo;


import java.util.HashMap;

public class ViewManager implements Disposable {
    private static ViewManager instance;

    public static ViewManager ins() {
        if(instance == null) instance = new ViewManager();
        return instance;
    }

    private HashMap<ViewIndex, BaseView> dicView = new HashMap<>();
    private BaseView currentView;

    public BaseView GetView(ViewIndex viewIndex) {
        return  dicView.get(viewIndex);
    }

    public BaseView GetCurrentView() {
        return currentView;
    }

    public void switchView(final ViewIndex viewIndex, final ViewParam viewParam, final IQDo iqDo) {
/*
        if(viewIndex == ViewIndex.GAME || viewIndex == ViewIndex.CONTROL_SETTING) {
            QAdsHelper.showBanner(false);
        }else {
            QAdsHelper.showBanner(true);
        }
*/

        if(currentView != null) {
            currentView.hideView(new IQDo() {
                @Override
                public void invoke() {
                    currentView.setVisible(false);
                    OnShowNextView(viewIndex,viewParam);
                    if(iqDo != null) iqDo.invoke();
                }
            });
        }else {
            OnShowNextView(viewIndex,viewParam);
        }


    }

    public void switchView(ViewIndex viewIndex) {
        switchView(viewIndex, null, null);
    }

    public void switchView(ViewIndex viewIndex, final ViewParam viewParam) {
        switchView(viewIndex, viewParam, null);
    }

    public void switchView(ViewIndex viewIndex, IQDo iqDo) {
        switchView(viewIndex, null, iqDo);
    }

    private void OnShowNextView(ViewIndex viewIndex, ViewParam viewParam) {
        currentView = dicView.get(viewIndex);
        currentView.setup(viewParam);
        currentView.showView();
        currentView.setVisible(true);
    }

    public void Setup(BaseView[] baseViews) {
        dicView.clear();
        currentView = null;
        for (BaseView v: baseViews) {
            AddView(v);
        }
    }

    public void AddView(BaseView baseView) {
        dicView.put(baseView.getIndex(),baseView);
    }

    public void RemoveView(ViewIndex viewIndex) {
        BaseView view = dicView.get(viewIndex);
        view.release();
        if(currentView == view) currentView = null;
        dicView.remove(viewIndex);
    }

    public ViewIndex getCurView() {
        if(currentView == null) return ViewIndex.NONE;
        return currentView.getIndex();
    }

    @Override
    public void dispose() {
        instance = null;
    }
}
