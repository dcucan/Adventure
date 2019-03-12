package droz00.adventure;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private List<Runnable> subscribers;

    public Observable() {
        subscribers = new ArrayList<>();
    }

    public void subscribe(Runnable callback) {
        subscribers.add(callback);
    }

    public void notifySubscribers() {
        for(Runnable callback : subscribers) {
            callback.run();
        }
    }

}
