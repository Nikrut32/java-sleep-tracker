package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private LocalDateTime startSession;
    private LocalDateTime endSession;
    private SleepQuality sleepQuality;

    SleepingSession(String session) {
        String[] separation = session.split(";");
        startSession = LocalDateTime.parse(separation[0], formatter);
        endSession = LocalDateTime.parse(separation[1], formatter);
        sleepQuality = SleepQuality.valueOf(separation[2]);
    }

    public LocalDateTime getStartSession() {
        return startSession;
    }

    public LocalDateTime getEndSession() {
        return endSession;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }
}
