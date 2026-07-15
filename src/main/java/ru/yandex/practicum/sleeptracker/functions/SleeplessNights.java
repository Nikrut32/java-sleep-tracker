package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SleeplessNights implements SleepAnalysisResult<Long> {
    @Override
    public Long apply(List<SleepingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return 0L;
        }

        LocalDate minDate = sessions.stream()
                .map(session -> session.getStartSession().toLocalDate())
                .min(LocalDate::compareTo)
                .orElseThrow();

        LocalDate maxDate = sessions.stream()
                .map(session -> session.getEndSession().toLocalDate())
                .max(LocalDate::compareTo)
                .orElseThrow();

        Set<LocalDate> allNights = Stream.iterate(minDate, date -> !date.isAfter(maxDate),
                date -> date.plusDays(1))
                .collect(Collectors.toSet());

        return allNights.stream()
                .filter(night -> {
                    LocalDateTime checkStart = night.atStartOfDay();
                    LocalDateTime checkEnd = night.atTime(6,0);

                    return sessions.stream().noneMatch(session -> {
                       LocalDateTime sleepStart = session.getStartSession();
                       LocalDateTime sleepEnd = session.getEndSession();

                       return sleepStart.isBefore(checkEnd) && sleepEnd.isAfter(checkStart);
                    });
                })
                .count();
    }

    @Override
    public String getDescription() {
        return "Количество бессонных ночей: ";
    }
}
