package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class AmountOfSessions implements SleepAnalysisResult<Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        return (long) sessions.size();
    }

    @Override
    public String getDescription() {
        return "Количество сессий: ";
    }
}

