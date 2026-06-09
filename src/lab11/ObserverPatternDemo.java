package lab11;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel("Tech Explained");

        MediaInterested media1 = new MediaInterested("CNN");
        MediaInterested media2 = new MediaInterested("FoxNews");

        channel.addObserver(media1);
        channel.addObserver(media2);

        String message1 = "Observer Pattern in Java";
        channel.uploadVideo(message1);

        channel.removeObserver(media2);

        String message2 = "Singleton Pattern in Java";
        channel.uploadVideo(message2);
    }
}
