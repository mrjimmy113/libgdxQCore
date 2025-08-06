package com.quang.core.behavior.fsm;

import com.quang.core.behavior.base.Behavior;
import com.quang.core.behavior.base.BehaviorUpdater;

public class FSMSystem extends Behavior {

    public final static IFSMState Empty_State = new FSMState_Empty(null);

    public IFSMState currentState;

    public FSMSystem(BehaviorUpdater updater) {
        super(updater);
    }

    public FSMSystem(BehaviorUpdater updater, boolean isAdd) {
        super(updater, isAdd);
    }

    public void gotoState(IFSMState newState) {
        if(currentState != null) {
            currentState.onExit();
        }
        currentState = newState;
        currentState.onEnter();
    }

    @Override
    protected void update() {
        if(currentState != null) currentState.onUpdate();
        onSysUpdate();
    }

    protected void onSysUpdate() {}
}
