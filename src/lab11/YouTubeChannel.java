package lab11;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannel {
    private String channelName;
    private List<MediaInterested> observers = new ArrayList<>();

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    public void addObserver(MediaInterested observer) {
        observers.add(observer);
    }

    public void removeObserver(MediaInterested observer) {
        observers.remove(observer);
    }

    public void uploadVideo(String title) {
        System.out.println("{"+ channelName + "} uploaded a new video: " + title);
        for (MediaInterested observer : observers) {
            observer.notifyObserver(title);
        }
    }
}
