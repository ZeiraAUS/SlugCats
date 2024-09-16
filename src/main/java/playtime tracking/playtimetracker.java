import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class playtimetracker {
    public static void main(String[] args) {
        String processName = "idea64.exe";

        while (true) {
            long loopstartTime = System.currentTimeMillis();

            String startTime = getProcessStartTime(processName);

            if (startTime != null && !startTime.isEmpty()) {
                System.out.println("Start Time for " + processName + ": " + startTime);

                LocalDateTime startDateTime = parseDateTime(startTime);
                if (startDateTime != null) {
                    Duration runtime = Duration.between(startDateTime, LocalDateTime.now());

                    System.out.println("Real-Time Running for " + processName + ": " +
                            runtime.toHoursPart() + " H " +
                            runtime.toMinutesPart() + " M " +
                            runtime.toSecondsPart() + " S ");
                }
            } else {
                System.out.println(processName + " is not running.");
            }

            // Loop delay calculation
            long loopendTime = System.currentTimeMillis();
            long timeDiff = loopendTime - loopstartTime;

            try {
                // Adjust sleep time based on the code execution time
                long sleepTime = 1000 -timeDiff;
                if (sleepTime > 0) {
                  Thread.sleep(sleepTime);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Clear the console (optional for a cleaner display)
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static String getProcessStartTime(String processName) {
        String startTime = "";
        try {
            String command = String.format("wmic process where name=\"%s\" get CreationDate", processName);
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("CreationDate")) continue; // Skip the header line
                if (!line.trim().isEmpty()) {
                    startTime = line.trim();
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return startTime;
    }

    public static LocalDateTime parseDateTime(String wmicDateTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            return LocalDateTime.parse(wmicDateTime.substring(0, 14), formatter);
        } catch (Exception e) {
            System.out.println("Error parsing date time: " + e.getMessage());
            return null;
        }
    }
}