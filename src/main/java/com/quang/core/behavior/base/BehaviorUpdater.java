package com.quang.core.behavior.base;

import com.badlogic.gdx.math.MathUtils;

import java.util.HashSet;
import java.util.Set;

public class BehaviorUpdater {

    private final Set<Behavior> behaviors = new HashSet<>();
    private final Set<Behavior> behaviorAdd = new HashSet<>();
    private final Set<Behavior> behaviorRemove = new HashSet<>();
    public float DELTA_TIME = 0;
    public float NON_SCALE_DELTA_TIME = 0;

    public BehaviorUpdater(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public void addBehavior(Behavior behavior) {
        behaviorAdd.add(behavior);
        behaviorRemove.remove(behavior);
    }

    public void removeBehavior(Behavior behavior) {
        behaviorAdd.remove(behavior);
        behaviorRemove.add(behavior);
    }

    private void processAdd() {
        if(behaviorAdd.isEmpty()) return;
        behaviors.addAll(behaviorAdd);
        for (Behavior b : behaviorAdd) {
            b.onEnable();
        }
        behaviorAdd.clear();
    }

    private void processRemove() {
        if(behaviorRemove.isEmpty()) return;
        behaviors.removeAll(behaviorRemove);
        for (Behavior b : behaviorRemove) {
            b.onDisable();
        }
        behaviorRemove.clear();
    }

    private boolean isDebug = false;
    private int debugIndex = 0;
    private static final float Debug_Offset_Y = 0.5f;
    private static final float Debug_Font_Size = 0.5f;

    public void update(float delta) {
        processRemove();
        processAdd();
        NON_SCALE_DELTA_TIME = delta;
        DELTA_TIME = MathUtils.clamp(delta, Float.MIN_VALUE, 1/30f);

        if(isDebug) {
            debugIndex = 0;
        }

        if(isDebug) {
            debugIndex += 1;
        }

        for (Behavior b: behaviors) {
            if(isDebug) {
                debugIndex++;
            }
            b.updateBehavior();
        }


    }

    public void clearBehavior() {
        behaviors.clear();
        behaviorAdd.clear();
        behaviorRemove.clear();
    }

    public boolean isBehaviorActive(Behavior b) {
        if(behaviors.contains(b)) return true;
        if(behaviorAdd.contains(b)) return true;
        return false;
    }


}
