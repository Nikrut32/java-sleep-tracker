package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AverageSessionDuration implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        return (long) sessions.stream()
                .mapToLong(session -> Duration.between(session.getStartSession(), session.getEndSession()).toMinutes())
                .average()
                .orElse(0);
    }
}