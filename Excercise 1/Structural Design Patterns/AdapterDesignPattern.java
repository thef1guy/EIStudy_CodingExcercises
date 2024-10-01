package EIDocs;

public class AdapterDesignPattern {
    //Making an adapter to convert an audio-only player to play video as well
    //Target interface
    public static interface AudioPlayer {
        public void play(String fType, String fName);
    }

    //Adaptee interface
    public static interface AdvancedPlayer {
        public void playMP4(String fName);
        public void playVLC(String fName);
    }

    public static class MP4Player implements AdvancedPlayer {
        public void playMP4(String fName) {
            System.out.println("Now playing: " + fName + ".mp4");
        }

        public void playVLC(String fName) {
            //nothing
        }
    }

    public static class VLCPlayer implements AdvancedPlayer {
        public void playVLC(String fName) {
            System.out.println("Now playing: " + fName + ".vlc");
        }

        public void playMP4(String fName) {
            //nothing
        }
    }

    //Adapter class
    public static class Adapter implements AudioPlayer {

        AdvancedPlayer player;

        public Adapter(String fType) {

            if (fType.equalsIgnoreCase("mp4")) {
                player = new MP4Player();
            } else if (fType.equalsIgnoreCase("vlc")) {
                player = new VLCPlayer();
            }
        }

        public void play(String fType, String fName) {
            if (fType.equalsIgnoreCase("mp4")) {
                player.playMP4(fName);
            } else if (fType.equalsIgnoreCase("vlc")) {
                player.playVLC(fName);
            }
        }
    }

    public static class Player implements AudioPlayer {

        @Override
        public void play(String fType, String fName) {
            
            if (fType.equalsIgnoreCase("mp3")) {
                System.out.println("Now playing: " + fName + ".mp3");
            }
            else if (fType.equalsIgnoreCase("mp4") || fType.equalsIgnoreCase("vlc")) {
                Adapter adapter = new Adapter(fType);  
                adapter.play(fType, fName);            
            } else {
                System.out.println("Invalid media type: " + fType + " not supported.");
            }
        }
    }

    public static class Main {
        public static void main(String args[]) {
            Player player = new Player();

            player.play("mp3", "GHOST TOWN");    
            player.play("mp4", "Movie1");       
            player.play("vlc", "TENET");         
        }
    }
}
