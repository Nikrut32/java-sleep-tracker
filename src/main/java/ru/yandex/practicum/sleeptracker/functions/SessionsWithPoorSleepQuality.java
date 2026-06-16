package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class SessionsWithPoorSleepQuality implements SleepAnalysisResult<Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        return sessions.stream()
                .filter(session -> session.getSleepQuality() == SleepQuality.BAD)
                .count();
    }

    @Override
    public String getDescription() {
        return "Количество сессий с плохим качеством сна: ";
    }
}
