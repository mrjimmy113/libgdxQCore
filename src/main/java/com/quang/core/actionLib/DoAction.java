package com.quang.core.actionLib;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.quang.core.utils.IQDo;


public class DoAction extends TemporalAction {
    private IQDo doSomething;

    public DoAction(IQDo doSomething, float duration) {
        super(duration);
        this.doSomething = doSomething;
    }



    @Override
    protected void begin() {
        doSomething.invoke();
    }

    @Override
    protected void update(float percent) {

    }
}
