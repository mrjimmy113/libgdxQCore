package com.quang.core.behavior.base;

public class Behavior {
    protected BehaviorUpdater updater;

    void updateBehavior() {
        update();
    }

    public Behavior(BehaviorUpdater updater) {
        this(updater, true);
    }

    public Behavior(BehaviorUpdater updater, boolean isActive) {
        this.updater = updater;
        setActive(isActive);
    }

    public void setActive(boolean isActive) {
        boolean curActive = isActive();
        if(curActive == isActive) return;

        if(isActive) updater.addBehavior(this);
        else updater.removeBehavior(this);
    }

    public boolean isActive() {
        if(updater == null) return false;
        return updater.isBehaviorActive(this);
    }

    public void destroy() {
        setActive(false);
        onDestroy();
    }

    public float deltaTime() {
        return updater.DELTA_TIME;
    }

    protected void onEnable() {}
    protected void onDisable() {}
    protected void onDestroy() {}
    protected void update() {}

    public BehaviorUpdater getUpdater() {
        return updater;
    }
}
