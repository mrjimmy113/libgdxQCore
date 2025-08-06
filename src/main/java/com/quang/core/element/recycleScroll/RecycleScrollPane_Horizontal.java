package com.quang.core.element.recycleScroll;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.quang.core.element.QLabel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class RecycleScrollPane_Horizontal extends ScrollPane {

    protected List<? extends RecycleScrollItemData> itemData;
    protected List<RecycleScrollItem_Horizontal<?>> itemList;
    protected Group groupImg;
    protected Group groupLabel;
    protected Group groupAlone;

    protected Vector2 itemSize = new Vector2();
    protected int itemPerPage = 0;
    protected int row = 1;
    protected int extraItemPerPage = 0;

    public RecycleScrollPane_Horizontal(Class<? extends RecycleScrollItem_Horizontal<?>> itemClass, int size) {
        super(new Group());
        row = 1;
        create(itemClass,size);
    }

    public RecycleScrollPane_Horizontal(Class<? extends RecycleScrollItem_Horizontal<?>> itemClass, int size, float width, float height) {
        super(new Group());
        row = 1;
        setSize(width,height);
        create(itemClass,size);
    }

    public RecycleScrollPane_Horizontal(Class<? extends RecycleScrollItem_Horizontal<?>> itemClass, int size, int column) {
        super(new Group());
        this.row = column;
        create(itemClass,size);
    }

    public RecycleScrollPane_Horizontal(Class<? extends RecycleScrollItem_Horizontal<?>> itemClass, int size, int column, int extraItemPerPage) {
        super(new Group());
        this.row = column;
        this.extraItemPerPage = extraItemPerPage;
        create(itemClass,size);
    }


    private void create(Class<? extends RecycleScrollItem_Horizontal<?>> itemClass, int size) {
        itemList = new ArrayList<>();
        setupGroup();
        try{
            for (int i = 0 ; i < size;i++) {
                RecycleScrollItem_Horizontal<?> item = itemClass.getConstructor(RecycleScrollPane_Horizontal.class).newInstance(this);
                itemList.add(item);
            }

            RecycleScrollItem_Horizontal<?> item = itemList.get(0);
            itemSize.set(item.getWidth(),item.getHeight());

        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                InstantiationException a) {
            a.printStackTrace();
            //throw new RuntimeException(a);
        }
    }

    public void setupGroup() {
        groupImg = new Group();
        groupLabel = new Group();
        groupAlone = new Group();
        getContent().addActor(groupImg);
        getContent().addActor(groupLabel);
        getContent().addActor(groupAlone);
    }


    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        if(itemSize == null) return;

        calItemPerPage();
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        calItemPerPage();
    }

    public Group getContent() {
        return (Group) getActor();
    }

    public void setupGroupWidth(float width) {
        groupImg.setWidth(width);
        groupLabel.setWidth(width);
        groupAlone.setWidth(width);
    }

    public void setItemData(List<? extends RecycleScrollItemData> itemData) {
        if(itemData == null) return;
        Group content = getContent();
        float width = getContentWidth(itemData);
        content.setWidth(width);
        layout();
        setupGroupWidth(width);
        this.itemData = itemData;
        setActor(content);

        int index = 0;

        for(RecycleScrollItem_Horizontal<?> item : itemList) {
            if(index >= itemData.size()) {
                item.setVisible(false);
                continue;
            }

            item.setVisible(true);
            item.setPosFromIndex(index, content.getWidth());
            //item.setData(itemData.get(index));
            index++;

        }
        scrollX(0);
        startIndex = -1;
        setupFromScroll();
    }

    public void forceUpdateScroll() {
        scrollX(0);
        startIndex = -1;
        setupFromScroll();
    }

    protected float getContentWidth(List<? extends RecycleScrollItemData> itemData) {
        return MathUtils.ceil(itemData.size() * 1f / row) * itemSize.x;
    }

    public int getRow() {
        return row;
    }

    private int startIndex = 0;

    public void scrollToPage(int pageIndex) {
        if(itemData == null) return;
        if(pageIndex > itemData.size()) return;
        scrollX(pageIndex * itemSize.x);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(itemList.isEmpty()) return;
        if(itemData == null) return;
        setupFromScroll();
    }

    public void setupFromScroll() {
        int curStartIndex = MathUtils.floor(getScrollX() / itemSize.x * row);
        curStartIndex /= row;
        curStartIndex *= row;
        curStartIndex = MathUtils.clamp(curStartIndex,0,Integer.MAX_VALUE);
        if(curStartIndex != startIndex) {
            startIndex = curStartIndex;
            updateFromScroll();
        }
    }

    public int getCurPageIndex() {
        return MathUtils.floor((getScrollX() + getWidth() * 0.5f) / itemSize.x);
    }

    public void updateFromScroll() {
        int limit = itemPerPage + startIndex + 1;
        limit = MathUtils.clamp(limit,0, itemData.size());

        for (int i = startIndex; i < limit; i++) {
            RecycleScrollItem_Horizontal item = itemList.get(i % itemList.size());
            item.setPosFromIndex(i,getContent().getWidth());
            item.setData(itemData.get(i));
        }
    }

    protected void calItemPerPage() {
        itemPerPage = MathUtils.ceil(getWidth() / itemSize.x) * row + extraItemPerPage;
    }

    public void customAddActor(Actor actor) {
        customAddActor(actor,!(actor instanceof QLabel));
    }

    public void customAddActor(Actor actor, boolean isToElement) {
        if(isToElement) groupImg.addActor(actor);
        else groupLabel.addActor(actor);
    }

}
