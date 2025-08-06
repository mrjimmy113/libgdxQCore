package com.quang.core.element.scrollPane;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Array;

public class QPageScrollPane extends ScrollPane {
    private Array<IQPageScrollElement> elements;
    private Array<QPageScrollElementData> data;

    private boolean wasPanDragFling = false;
    private Group content;

    private int pageIndex = -1;
    private float pageWidth;
    private float cachedScrollX;
    private  IQPageScrollBtn btn;

    public QPageScrollPane (Array<IQPageScrollElement> elements,
                            Array<QPageScrollElementData> data, int width, int height) {
        super(null);
        content = new Group();
        this.elements = elements;
        this.data = data;
        pageWidth = elements.get(0).getActor().getWidth();

        int index = 0;
        for (IQPageScrollElement e: elements) {
            content.addActor(e.getActor());
            e.getActor().setX(width *index);
            index++;
        }

        setSize(width,height);
        content.setSize(0,height);
        updatePage(0);
        setActor(content);
    }

    public void setBtn(IQPageScrollBtn btn) {
        this.btn = btn;
    }


    public void setData(Array<QPageScrollElementData> newData) {
        this.data = newData;
        cachedScrollX = -100;
        content.setSize(getWidth() * data.size,getHeight());
        btn.setPageNum(newData.size);
        setActor(content);
        updatePageForce(0);

    }

    @Override
    public void act (float delta) {
        super.act(delta);

        int runtimePageIndex = MathUtils.floor((getScrollX() + getWidth() * 0.5f) / getWidth());
        updatePage(runtimePageIndex);

        if (wasPanDragFling && !isPanning() && !isDragging() && !isFlinging()) {
            wasPanDragFling = false;
            scrollToPage();
        } else {
            if (isPanning() || isDragging() || isFlinging()) {
                wasPanDragFling = true;
            }
        }

    }



    private void scrollToPage () {

        final float scrollX = getScrollX();


        if (scrollX >= getMaxX() || scrollX <= 0) return;

        if (data.size > 0) {
            float pageX = pageIndex * pageWidth;
            setScrollX(MathUtils.clamp(pageX - (getWidth() - pageWidth) / 2, 0, getMaxX()));
        }
    }

    private Array<Integer> updateList = new Array<>();

    private void updatePageForce(int index) {
        pageIndex = -1;
        updatePage(index);
    }

    private void updatePage(int index) {
        if(cachedScrollX == getScrollX()) return;
        cachedScrollX = getScrollX();
        if(pageIndex == index) return;
        pageIndex = index;
        if(btn != null) btn.setPageIndex(pageIndex);
        updateList.clear();
        updateList.add(pageIndex);
        float renderStart = getScrollX();
        float renderEnd = renderStart + getWidth() * 2f;

        for (int i = 1 ; i < 10;i++) {
            int nextPageIndex = pageIndex + i;
            if(nextPageIndex >= data.size) break;
            float pageXStart = pageWidth * nextPageIndex;
            float pageXEnd = pageXStart + pageWidth;

            if(pageXStart <= renderEnd && renderStart <= pageXEnd) {
                updateList.add(nextPageIndex);
            }else break;
        }

        for (int i = 1 ; i < 10;i++) {
            int previousPageIndex = pageIndex - i;
            if(previousPageIndex < 0) break;
            float pageXStart = pageWidth * previousPageIndex;
            float pageXEnd = pageXStart + pageWidth;


            if(pageXStart <= renderEnd && renderStart <= pageXEnd) {
                updateList.add(previousPageIndex);
            }else break;
        }

        for (Integer dataIndex : updateList) {
            int elementIndex = dataIndex % elements.size;
            QPageScrollElementData d = data.get(dataIndex);
            IQPageScrollElement e = elements.get(elementIndex);
            e.getActor().setX(dataIndex * getWidth());
            e.applyData(d);
        }


    }
}
