package com.quang.core.behavior.fsm;

public class FSMState<T extends FSMSystem> implements IFSMState{

    protected T sys;

    public FSMState(T sys) {
        this.sys = sys;
    }

    public void onEnter() {}
    public void onReEnter() {}
    public void onUpdate(){};
    public void onExit(){};

}
