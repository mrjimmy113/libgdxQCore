package com.quang.core.ui.view;

import com.quang.core.ui.QViewStage;

public class EmptyView extends BaseView {

    public EmptyView(QViewStage viewStage) {
        super(viewStage);
    }

    @Override
    public ViewIndex getIndex() {
        return ViewIndex.EMPTY;
    }

    @Override
    public void setup(ViewParam param) {

    }

}
