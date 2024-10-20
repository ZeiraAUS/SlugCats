package com.SlugCats.timetracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class playtimetracker {
    private String processName;
    private long playTime;

    public playtimetracker(String processName) {
        this.processName = processName;
        this.playTime = 0;
    }

    public long trackPlayTime() {
        while (true) {
            long loopstartTime = System.currentTimeMillis();

            String startTime = getProcessStartTime(processName);

            if (startTime != null && !startTime.isEmpty()) {

                LocalDateTime startDateTime = parseDateTime(startTime);

                if (startDateTime != null) {
                    Duration runtime = Duration.between(startDateTime, LocalDateTime.now());

                    playTime = runtime.getSeconds();
                    playtimemonitoring.trackedPlayTime = playTime;

                    System.out.println(processName + ": " +
                            runtime.toHoursPart() + " H " +
                            runtime.toMinutesPart() + " M " +
                            runtime.toSecondsPart() + " S ");
                }
            }
            else {
                break;
            }

            long loopendTime = System.currentTimeMillis();
            long timeDiff = loopendTime - loopstartTime;

            try {
                long sleepTime = 1000 - timeDiff;
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        return playTime;
    }

    public static String getProcessStartTime(String processName) {
        String startTime = "";
        try {
            String command = String.format("wmic process where name=\"%s\" get CreationDate", processName);
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("CreationDate")) continue;
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