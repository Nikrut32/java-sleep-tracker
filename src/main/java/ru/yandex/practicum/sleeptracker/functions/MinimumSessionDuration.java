package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;

public class MinimumSessionDuration implements SleepAnalysisResult<Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        return sessions.stream()
                .mapToLong(session -> Duration.between(session.getStartSession(), session.getEndSession()).toMinutes())
                .min()
                .orElse(0);

    }

    @Override
    public String getDescription() {
        return "Минимальная продолжительность сессии (в минутах): ";
    }
}
