package com.quang.core.element;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.Hashtable;

public class QSortedGroup extends Group {

    private int id;

    private final Hashtable<String, Group> sortedGroups = new Hashtable<>();

    public QSortedGroup() {
        setTransform(false);
    }

    public QSortedGroup(int id) {
        this.id = id;
        setTransform(false);
    }

    @Override
    public void addActor(Actor actor) {
        if(actor instanceof IQSortedActor) {
            IQSortedActor sortedActor = (IQSortedActor) actor;
            if(sortedGroups.containsKey(sortedActor.getAsset())) {
                sortedGroups.get(sortedActor.getAsset()).addActor(sortedActor.getActor());
            }else {
                Group group = new Group();
                group.setTransform(false);
                group.addActor(sortedActor.getActor());
                sortedGroups.put(sortedActor.getAsset(), group);
                super.addActor(group);
            }
        }else {
            super.addActor(actor);
        }


    }

    @Override
    public void clear() {
        super.clear();
        for (Group g : sortedGroups.values()) {
            g.clear();
        }
        sortedGroups.clear();
    }

    @Override
    public String toString() {
        return id + "-" + super.toString();
    }
}
