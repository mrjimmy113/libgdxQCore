package com.quang.core.behavior.fsm;

public interface IFSMState {

    void onEnter();
    void onReEnter();
    void onUpdate();
    void onExit();
}
