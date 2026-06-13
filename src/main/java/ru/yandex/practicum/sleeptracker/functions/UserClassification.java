package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class UserClassification implements Function<List<SleepingSession>, String> {
    @Override
    public String apply(List<SleepingSession> sessions) {
        long countOwl = sessions.stream()
                .filter(session -> {
                    if (!session.getStartSession().toLocalDate().isEqual(session.getEndSession().toLocalDate())) {
                        return session.getStartSession().toLocalTime().isAfter(LocalTime.of(23, 0))
                                && session.getEndSession().toLocalTime().isAfter(LocalTime.of(9, 0));
                    }
                    return false;
                })
                .count();
        long countLark = sessions.stream()
                .filter(session -> {
                    if (!session.getStartSession().toLocalDate().isEqual(session.getEndSession().toLocalDate())) {
                        return session.getStartSession().toLocalTime().isBefore(LocalTime.of(22, 0))
                                && session.getEndSession().toLocalTime().isBefore(LocalTime.of(7, 0));
                    }
                    return false;
                })
                .count();
        long countPigeon = sessions.size() - countLark - countOwl;

        if (countOwl > countLark && countOwl > countPigeon) {
            return "Сова";
        } else if (countLark > countOwl && countLark > countPigeon) {
            return  "Жаворонок";
        } else if (countLark == countOwl && countPigeon > countOwl) {
            return "Голубь";
        }
        return "Голубь";
    }
}
