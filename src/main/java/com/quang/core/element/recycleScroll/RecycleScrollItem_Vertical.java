package com.quang.core.element.recycleScroll;

import com.badlogic.gdx.math.MathUtils;

public class RecycleScrollItem_Vertical<T extends RecycleScrollItemData> {

    private float x;
    private float y;
    private float width;
    private float height;
    private boolean isVisible;

    protected RecycleScrollPane_Vertical pane;

    public RecycleScrollItem_Vertical(RecycleScrollPane_Vertical pane) {
        this.pane = pane;
    }

    public void setPosFromIndex(int index, float scrollHeight) {
        setX(index % pane.getColumn() * getWidth());
        int rowIndex = MathUtils.floor(index * 1f / pane.getColumn()) + 1;
        float y = scrollHeight - rowIndex * getHeight();
        setY(y);

        setElementPos(index);
    }

    public void setElementPos(int index) {}

    public void setData(T data) {

    }

    public void setSize(float width, float height) {
        setWidth(width);
        setHeight(height);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
