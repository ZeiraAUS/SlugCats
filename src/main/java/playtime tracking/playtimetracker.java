import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class playtimetracker {
    public static void main(String[] args) {
        String processName = "idea64.exe"; //change this hard code to user input
        // for single time
//        List<String> runningTimes = getProcessRunTimes(processName);
//
//        if (!runningTimes.isEmpty()) {
//            System.out.println("Success");
//            for (String time : runningTimes) {
//                System.out.println(time);
//            }
//        } else {
//            System.out.println("Fail");
//        }

        //for constant live update
        while (true) {
            List<String> runningTimes = getProcessRunTimes(processName);

            if (!runningTimes.isEmpty()) {
                System.out.println("CPU Times for " + processName + ":");
                for (String time : runningTimes) {
                    System.out.println(time);
                }
            } else {
                System.out.println(processName + " is not running.");
            }

            try {
                // Delay before the next update (e.g., refresh every 5 seconds)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Clear the console (optional for a cleaner display)
            // This command might be platform-dependent. Works on some terminals.
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

    public static List<String> getProcessRunTimes(String processName) {
        List<String> runningTimes = new ArrayList<>();
        try {
            String command = String.format("tasklist /v /FI \"IMAGENAME eq %s\"", processName);
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            Pattern timePattern = Pattern.compile("\\d{1,2}:\\d{2}:\\d{2}");
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(processName.toLowerCase())){
                    Matcher matcher = timePattern.matcher(line);
                    if (matcher.find()) {
                        String cpuTime= matcher.group();
                        runningTimes.add(cpuTime);
                    }
                }
            }

            //Old code - changed it to regex instead see above
//            while ((line = reader.readLine()) != null) {
//                if (line.toLowerCase().contains(processName.toLowerCase())) {
//                    String[] columns = line.split("\\s{2,}");
//                    if (columns.length > 1) {
//                        String cpuTime = columns[columns.length - 1];
//
//                        int firstSpaceIndex = cpuTime.indexOf(' ');
//                        if (firstSpaceIndex != -1) {
//                            cpuTime = cpuTime.substring(0, firstSpaceIndex).trim();
//                        }
//
//                        if (!cpuTime.equalsIgnoreCase("N/A") && !cpuTime.isEmpty()) {
//                            runningTimes.add(cpuTime);
//                        }
//                    }
//                }
//            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return runningTimes;
    }
}