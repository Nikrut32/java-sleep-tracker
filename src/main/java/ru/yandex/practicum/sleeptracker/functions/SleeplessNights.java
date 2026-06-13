package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessNights implements Function<List<SleepingSession>, Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalTime sixAm = LocalTime.of(6, 0);
        return sessions.stream()
                .filter(session -> {
                    LocalTime startTime = session.getStartSession().toLocalTime();
                    LocalTime endTime = session.getEndSession().toLocalTime();
                    if (session.getStartSession().toLocalDate().isEqual(session.getEndSession().toLocalDate())) {
                        return (!startTime.isAfter(midnight) || !startTime.isBefore(sixAm))
                                && !startTime.equals(midnight);
                    }
                    return endTime.equals(midnight);
                })
                .count();
    }
}
