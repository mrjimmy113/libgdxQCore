package com.quang.core.behavior.base;

public class BehaviorTime {
    private static float TIME_SCALE = 1;

    private static float CACHE_TIME_SCALE = 1;

    public static float getTimeScale() {
        return TIME_SCALE;
    }

    public static void changeTimeScale(float timeScale) {
        TIME_SCALE = timeScale;
        CACHE_TIME_SCALE = timeScale;
    }

    public static void stopTime() {
        TIME_SCALE = 0;
    }

    public static void startTime() {
        TIME_SCALE = CACHE_TIME_SCALE;
    }
}
