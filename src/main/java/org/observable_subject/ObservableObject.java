package org.observable_subject;

import java.util.ArrayList;
import java.util.List;

public abstract class ObservableObject {
    protected final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public abstract void checkObservers();
}
