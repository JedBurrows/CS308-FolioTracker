package utils;

import java.util.Observable;

public class CoolObservable extends Observable {

    public void setChangedAndNotifyObservers() {
        setChanged();
        notifyObservers();
    }
}
