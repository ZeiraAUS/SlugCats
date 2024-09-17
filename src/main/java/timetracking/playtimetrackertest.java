public class playtimetrackertest {
    public static void main(String[] args){
        String processName = "idea64.exe"; // change this to user input

        playtimetracker tracker = new playtimetracker(processName);

        tracker.trackPlayTime();
    }
}
