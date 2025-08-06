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

public class RecycleScrollPane_Vertical extends ScrollPane {

    protected List<? extends RecycleScrollItemData> itemData;
    protected List<RecycleScrollItem_Vertical> itemList;
    protected Group groupImg;
    protected Group groupLabel;
    protected Group groupAlone;

    protected Vector2 itemSize = new Vector2();
    protected int itemPerPage = 0;
    protected int column = 1;
    protected int extraItemPerPage = 0;

    public RecycleScrollPane_Vertical(Class<? extends RecycleScrollItem_Vertical> itemClass, int size) {
        super(new Group());
        column = 1;
        create(itemClass,size);
    }

    public RecycleScrollPane_Vertical(Class<? extends RecycleScrollItem_Vertical> itemClass, int size, float width, float height) {
        super(new Group());
        setSize(width,height);
        column = 1;
        create(itemClass,size);
    }

    public RecycleScrollPane_Vertical(Class<? extends RecycleScrollItem_Vertical> itemClass, int size, int column) {
        super(new Group());
        this.column = column;
        create(itemClass,size);
    }

    public RecycleScrollPane_Vertical(Class<? extends RecycleScrollItem_Vertical> itemClass, int size, int column, int extraItemPerPage) {
        super(new Group());
        this.column = column;
        this.extraItemPerPage = extraItemPerPage;
        create(itemClass,size);
    }


    private void create(Class<? extends RecycleScrollItem_Vertical> itemClass, int size) {
        itemList = new ArrayList<>();
        setupGroup();
        try{
            for (int i = 0 ; i < size;i++) {
                RecycleScrollItem_Vertical item = itemClass.getConstructor(RecycleScrollPane_Vertical.class).newInstance(this);
                itemList.add(item);
            }

            RecycleScrollItem_Vertical item = itemList.get(0);
            itemSize.set(item.getWidth(),item.getHeight());

        }catch (NoSuchMethodException a) {
            throw new RuntimeException(a);
        } catch (InstantiationException b) {
            throw new RuntimeException(b);
        }catch (IllegalAccessException c) {
            throw new RuntimeException(c);
        }catch (InvocationTargetException d) {
            throw new RuntimeException(d);
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
    public void setHeight(float height) {
        super.setHeight(height);
        calItemPerPage();
    }

    public Group getContent() {
        return (Group) getActor();
    }

    public void setupGroupHeight(float height) {
        groupImg.setHeight(height);
        groupLabel.setHeight(height);
        groupAlone.setHeight(height);
    }

    public void setItemData(List<? extends RecycleScrollItemData> itemData) {
        if(itemData == null) return;
        Group content = getContent();
        float height = getContentHeight(itemData);
        content.setHeight(height);
        layout();
        setupGroupHeight(height);
        this.itemData = itemData;
        setActor(content);

        int index = 0;

        for(RecycleScrollItem_Vertical item : itemList) {
            if(index >= itemData.size()) {
                item.setVisible(false);
                continue;
            }

            item.setVisible(true);
            item.setPosFromIndex(index, content.getHeight());
            //item.setData(itemData.get(index));
            index++;

        }
        scrollY(0);
        startIndex = -1;
        setupFromScroll();
    }

    protected float getContentHeight(List<? extends RecycleScrollItemData> itemData) {
        return MathUtils.ceil(itemData.size() * 1f / column) * itemSize.y;
    }

    public int getColumn() {
        return column;
    }

    private int startIndex = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        if(itemList.isEmpty()) return;
        if(itemData == null) return;
        setupFromScroll();
    }

    public void setupFromScroll() {
        int curStartIndex = MathUtils.floor(getScrollY() / itemSize.y * column);
        curStartIndex /= column;
        curStartIndex *= column;
        curStartIndex = MathUtils.clamp(curStartIndex,0,Integer.MAX_VALUE);
        if(curStartIndex != startIndex) {
            startIndex = curStartIndex;
            updateFromScroll();
        }
    }

    public void updateFromScroll() {
        int limit = itemPerPage + startIndex + 1;
        limit = MathUtils.clamp(limit,0, itemData.size());

        for (int i = startIndex; i < limit; i++) {
            RecycleScrollItem_Vertical item = itemList.get(i % itemList.size());
            item.setPosFromIndex(i,getContent().getHeight());
            item.setData(itemData.get(i));
        }
    }

    protected void calItemPerPage() {
        itemPerPage = MathUtils.ceil(getHeight() / itemSize.y) * column + extraItemPerPage;
    }

    public void customAddActor(Actor actor) {
        customAddActor(actor,!(actor instanceof QLabel));
    }

    public void customAddActor(Actor actor, boolean isToElement) {
        if(isToElement) groupImg.addActor(actor);
        else groupLabel.addActor(actor);
    }

    public int getCurPageIndex() {
        return MathUtils.floor((getScrollY() + getHeight() * 0.5f) / itemSize.y);
    }

    public void scrollToPage(int pageIndex) {
        if(itemData == null) return;
        if(pageIndex > itemData.size()) return;
        scrollY(pageIndex * itemSize.y);
    }

    public void forceUpdateScroll() {
        scrollY(0);
        startIndex = -1;
        setupFromScroll();
    }

}
