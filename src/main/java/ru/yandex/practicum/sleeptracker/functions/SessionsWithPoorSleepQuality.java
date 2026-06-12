package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class SessionsWithPoorSleepQuality implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        return sessions.stream()
                .filter(session -> session.getSleepQuality() == SleepQuality.BAD)
                .count();
    }
}
