package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.OptionalLong;
import java.util.function.Function;

public class MaximumSessionDuration implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        return sessions.stream()
                .mapToLong(session -> Duration.between(session.getStartSession(), session.getEndSession()).toMinutes())
                .max()
                .orElse(0);
    }
}
