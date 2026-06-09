package lab11;

public class MediaInterested {
    private String name;

    public MediaInterested(String name) {
        this.name = name;
    }

    public void notifyObserver(String message) {
        doSomeLogic(message);
    }

    public void doSomeLogic(String message) {
        System.out.println("["+name + "] received update: " + message);
    }
}